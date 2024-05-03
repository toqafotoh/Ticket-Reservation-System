package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.EntertainmentTicket;
import com.project.ticketreservation.Repositories.EntertainmentTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntertainmentTicketService {
    @Autowired
    private EntertainmentTicketRepository entertainmentTicketRepository;
    public List<EntertainmentTicket> getPassengerEntertainmentTickets(String passengerId){
        List<EntertainmentTicket> tickets = entertainmentTicketRepository.findByPassengerNationalId(passengerId);
        return tickets;
    }
}
