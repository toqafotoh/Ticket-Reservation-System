package com.project.ticketreservation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.Models.Ticket;
import com.project.ticketreservation.Repositories.TicketRepository;

@Service

public class TicketService {

    @Autowired
    private TicketRepository tc;

    public boolean addTicket(Ticket t) {
        tc.save(t);
        return true;
    }

}
