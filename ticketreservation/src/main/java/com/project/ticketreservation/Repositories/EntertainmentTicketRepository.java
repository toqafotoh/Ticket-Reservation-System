package com.project.ticketreservation.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.Models.EntertainmentTicket;

@Repository
public interface EntertainmentTicketRepository extends JpaRepository<EntertainmentTicket, Integer> {
    List<EntertainmentTicket> findByPassengerNationalId(String passengerId);
}
