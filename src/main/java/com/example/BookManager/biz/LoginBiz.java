package com.example.BookManager.biz;

import com.example.BookManager.model.Ticket;
import com.example.BookManager.model.User;
import com.example.BookManager.service.TicketService;
import com.example.BookManager.service.UserService;
import com.example.BookManager.utils.ConcurrentUtils;
import com.example.BookManager.utils.MD5;
import com.example.BookManager.utils.TicketUtils;
import com.example.BookManager.model.exceptions.LoginRegisterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.lang3.StringUtils;

import javax.security.auth.login.LoginException;
import java.util.Date;

@Service
public class LoginBiz {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    public String login(String email, String password) throws Exception {
        User user = userService.getUser(email);

        if (user == null)
            throw new LoginException("Email doesn't exist");
        if (!StringUtils.equals(MD5.next(password), user.getPassword()))
            throw new LoginException("Wrong password");
        //md5 encrypted password (cannot be decrypted)

        Ticket t = ticketService.getTicket(user.getId());
        if (t == null) {
            t = TicketUtils.next(user.getId());//generate user's ticket
            ticketService.addTicket(t);
            return t.getTicket();
        }
        if (t.getExpiredAt().before(new Date())) {
            ticketService.deleteTicket(t.getId());
        }

        //???
        t = TicketUtils.next(user.getId());//generate user's ticket
        ticketService.addTicket(t);
        ConcurrentUtils.setHost(user);
        return t.getTicket();
    }
    public void logout(String t){
        ticketService.deleteTicket(t);
    }
    public String register(User user)throws Exception{

        if(userService.getUser(user.getEmail())!=null)
            throw new LoginRegisterException("Email already exists");
        String plain = user.getPassword();
        String newPassword= MD5.next(plain);
        user.setPassword(newPassword);

        userService.addUser(user);
        Ticket t = TicketUtils.next(user.getId());//generate user's ticket
        ticketService.addTicket(t);
        ConcurrentUtils.setHost(user);
        return t.getTicket();
    }
}
