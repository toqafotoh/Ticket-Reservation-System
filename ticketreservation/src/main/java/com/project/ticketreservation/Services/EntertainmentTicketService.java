package com.project.ticketreservation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.models.EntertainmentTicket;
import com.project.ticketreservation.repositories.EntertainmentRepository;
import com.project.ticketreservation.repositories.EntertainmentTicketRepository;
import com.project.ticketreservation.repositories.PassengerRepository;
import com.project.ticketreservation.repositories.PaymentRepository;

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

    public List<EntertainmentTicket> getPassengerEntertainmentTickets(String passengerId) {
        List<EntertainmentTicket> tickets = entertainmentTicketRepository.findByPassengerNationalId(passengerId);
        return tickets;
    }

    private void validateEntities(EntertainmentTicket entertainmentTicket) {
        entertainmentRepository.findById(entertainmentTicket.getEntertainmentTourId())
                .orElseThrow(() -> new RuntimeException("Entertainment not found"));
        passengerRepository.findById(entertainmentTicket.getPassengerId())
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        paymentRepository.findById(entertainmentTicket.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public EntertainmentTicket createEntertainmentTicket(EntertainmentTicket entertainmentTicket) {
        validateEntities(entertainmentTicket);
        return entertainmentTicketRepository.save(entertainmentTicket);
    }
}
