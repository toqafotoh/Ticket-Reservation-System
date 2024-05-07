package com.project.ticketreservation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findBypaymentIdAndPrice(String paymentId, Double price);

    List<Ticket> findBypaymentId(String paymentId);
}
