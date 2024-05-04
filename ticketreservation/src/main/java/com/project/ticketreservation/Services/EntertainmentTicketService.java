package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.Entertainment;
import com.project.ticketreservation.Models.*;
import com.project.ticketreservation.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntertainmentTicketService {
    @Autowired
    private EntertainmentTicketRepository entertainmentTicketRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private EntertainmentRepository entertainmentRepository;
    public List<EntertainmentTicket> getPassengerEntertainmentTickets(String passengerId){
        List<EntertainmentTicket> tickets = entertainmentTicketRepository.findByPassengerNationalId(passengerId);
        return tickets;
    }
    private void validateEntities(EntertainmentTicket entertainmentTicket) {
        Entertainment entertainment = entertainmentRepository.findById(entertainmentTicket.getEntertainmentTourId())
                .orElseThrow(() -> new RuntimeException("Entertainment not found"));
        Passenger passenger = passengerRepository.findById(entertainmentTicket.getPassengerId())
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        Payment payment = paymentRepository.findById(entertainmentTicket.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
    public EntertainmentTicket createEntertainmentTicket(EntertainmentTicket entertainmentTicket) {
        validateEntities(entertainmentTicket);
        return entertainmentTicketRepository.save(entertainmentTicket);
    }
}
