package com.project.ticketreservation.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.models.FlightTicket;
import com.project.ticketreservation.models.Ticket;
// import com.project.ticketreservation.repositories.FlightRepository;
import com.project.ticketreservation.repositories.FlightTicketRepository;
import com.project.ticketreservation.repositories.TicketRepository;

@Service
public class FlightTicketService {
    @Autowired
    private FlightTicketRepository Repo;
    // @Autowired
    // private FlightRepository fr;
    @Autowired
    private TicketRepository tr;

    public List<FlightTicket> getTickets() {
        return Repo.findAll();
    }

    public boolean addFlightTicket(FlightTicket t) {
        Repo.save(t);
        return true;
    }

    public List<FlightTicket> getAllTickets(String nid) {
        List<FlightTicket> tickets = Repo.findByPassengerNationalId(nid);
        return tickets;
    }
    

    public FlightTicket getTicket(String nid) {
        Optional<FlightTicket> result = Repo.findByNationalID(nid);
        return result.orElse(null);
    }

    public List<FlightTicket> getPayTickets(String paymentId) {
        List<Ticket> ticketsWithPaymentId = tr.findBypaymentId(paymentId); // contain rows in ticket with the payment id
        List<Integer> ticketIds = new ArrayList<>();

        // Extract the ticket IDs from the Ticket entities
        for (Ticket ticket : ticketsWithPaymentId) {
            ticketIds.add(ticket.getTicketId());
            System.out.println("Ticket ID: " + ticket.getTicketId());
        }

        // Retrieve the FlightTicket entities based on the ticket IDs
        List<FlightTicket> flightTickets = new ArrayList<>();
        for (Integer ticketId : ticketIds) {
            Optional<FlightTicket> flightTicketOptional = Repo.findById(ticketId);
            if (flightTicketOptional.isPresent()) {
                FlightTicket flightTicket = flightTicketOptional.get();
                System.out.println("Found FlightTicket: " + flightTicket);
                flightTickets.add(flightTicket);
            } else {
                System.out.println("No FlightTicket found for ticket ID: " + ticketId);
            }
        }

        System.out.println("Number of FlightTickets found: " + flightTickets.size());
        return flightTickets;
    }
}
