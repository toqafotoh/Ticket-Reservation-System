package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.EntertainmentTicket;
import com.project.ticketreservation.Models.FlightTicket;
import com.project.ticketreservation.Repositories.FlightTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightTicketService {
    @Autowired
    private FlightTicketRepository flightTicketRepository;
    public List<FlightTicket> getPassengerFlightTickets(String passengerId){
        List<FlightTicket> tickets = flightTicketRepository.findByPassengerNationalId(passengerId);
        return tickets;
    }
}
