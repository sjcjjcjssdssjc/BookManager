package com.example.BookManager.interceptor;

import com.example.BookManager.model.Ticket;
import com.example.BookManager.model.User;
import com.example.BookManager.service.TicketService;
import com.example.BookManager.service.UserService;
import com.example.BookManager.utils.ConcurrentUtils;
import com.example.BookManager.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class HostInfoInterceptor implements HandlerInterceptor {
    //Authorization
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object Handler)
        throws Exception{

        String t = CookieUtils.getCookie("t",request);
        if(!StringUtils.isEmpty(t)){
            Ticket ticket=ticketService.getTicket(t);
            if(ticket!=null&&ticket.getExpiredAt().after(new Date())){
                User host=userService.getUser(ticket.getuserId());
                ConcurrentUtils.setHost(host);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
