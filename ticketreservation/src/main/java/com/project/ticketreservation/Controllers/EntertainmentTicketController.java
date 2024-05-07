package com.project.ticketreservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketreservation.models.EntertainmentTicket;
import com.project.ticketreservation.services.EntertainmentTicketService;

@RestController
@RequestMapping
public class EntertainmentTicketController {
    @Autowired
    private EntertainmentTicketService entertainmentTicketService;

    @PostMapping("entertainmentTicket")
    public EntertainmentTicket createEntertainmentTicket(EntertainmentTicket entertainmentTicket) {
        return entertainmentTicketService.createEntertainmentTicket(entertainmentTicket);
    }
}
