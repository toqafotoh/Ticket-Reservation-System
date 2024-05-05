package com.project.ticketreservation.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.Models.Ticket;

@Repository
public interface TicketReopsitory extends JpaRepository<Ticket, Integer> {
    List<Ticket> findBypaymentIdAndPrice(String paymentId, Double price);

    List<Ticket> findBypaymentId(String paymentId);
}
