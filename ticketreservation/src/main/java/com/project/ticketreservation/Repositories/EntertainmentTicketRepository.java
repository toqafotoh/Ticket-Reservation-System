package com.project.ticketreservation.Repositories;

import com.project.ticketreservation.Models.EntertainmentTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntertainmentTicketRepository extends JpaRepository<EntertainmentTicket,Integer> {
    List<EntertainmentTicket> findByPassengerNationalId(String passengerId);
}
