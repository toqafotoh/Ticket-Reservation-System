package com.project.ticketreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.models.Ticket;
import com.project.ticketreservation.repositories.TicketRepository;

@Service

public class TicketService {

    @Autowired
    private TicketRepository tc;

    public boolean addTicket(Ticket t) {
        tc.save(t);
        return true;
    }

}
