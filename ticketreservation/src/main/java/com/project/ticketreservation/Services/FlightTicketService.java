package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.Flight;
import com.project.ticketreservation.Models.FlightTicket;
import com.project.ticketreservation.Models.Ticket;
import com.project.ticketreservation.Repositories.FlightRepository;
import com.project.ticketreservation.Repositories.FlightTicketRepository;
import com.project.ticketreservation.Repositories.TicketReopsitory;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class flightTicketService {

    @Autowired
    private  FlightTicketRepository Repo ;
    @Autowired

    @Autowired
    private  FlightTicketRepository Repo ;
    private FlightTicketRepository flightTicketRepository;

    private FlightRepository fr;
    @Autowired
    private TicketReopsitory tr ;


    public List<FlightTicket> getTickets(){
        return Repo.findAll();
    }

    public boolean addFlightTicket(FlightTicket t){
        Repo.save(t);
        return true;
    }

    public void addFlight(Flight f)
    {
        fr.save(f);
    }


    public List<FlightTicket> getPassengerFlightTickets(String passengerId){
        List<FlightTicket> tickets = flightTicketRepository.findByPassengerNationalId(passengerId);
        return tickets;
    }
    public List<FlightTicket> getTickets(){
        return Repo.findAll();
    }

    public boolean addFlightTicket(FlightTicket t){
        Repo.save(t);
        return true;
    }

    public void addFlight(Flight f)
    {
        fr.save(f);
    }


    public FlightTicket getTicket(String nid){
        Optional<FlightTicket> result =  Repo.findByNationalID(nid);
        return result.orElse(null);
    }

 
    public List<FlightTicket> getPayTickets(String paymentId) {
        List<Ticket> ticketsWithPaymentId = tr.findBypaymentId(paymentId); //contain rows in ticket with the payment id
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


