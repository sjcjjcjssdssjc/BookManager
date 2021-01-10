package com.example.BookManager.service;

import com.example.BookManager.dao.TicketDAO;
import com.example.BookManager.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    public void addTicket(Ticket t){
        ticketDAO.addTicket(t);
    }

    public Ticket getTicket(int uid){
        return ticketDAO.SelectByUserId(uid);
    }

    public Ticket getTicket(String t){
        return ticketDAO.SelectByTicket(t);
    }

    public void deleteTicket(int id){
        ticketDAO.deleteById(id);
    }

    public void deleteTicket(String t){
        ticketDAO.deleteByTicket(t);
    }
}
