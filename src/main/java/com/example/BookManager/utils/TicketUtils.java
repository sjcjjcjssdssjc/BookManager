package com.example.BookManager.utils;

import com.example.BookManager.model.Ticket;
import org.joda.time.DateTime;

public class TicketUtils {
    //generate a new ticket
    public static Ticket next(int uid){

        Ticket ticket=new Ticket();
        ticket.setTicket(UuidUtils.next());
        ticket.setuserId(uid);

        DateTime expiredTime=new DateTime();
        expiredTime=expiredTime.plusMonths(3);
        ticket.setExpiredAt(expiredTime.toDate());

        return ticket;
    }
}
