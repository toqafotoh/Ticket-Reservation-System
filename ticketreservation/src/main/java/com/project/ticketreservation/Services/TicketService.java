package com.project.ticketreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.models.Ticket;
import com.project.ticketreservation.repositories.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public boolean addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
        return true;
    }

}
