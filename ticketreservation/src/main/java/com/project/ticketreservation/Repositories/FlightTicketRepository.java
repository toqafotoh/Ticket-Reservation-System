package com.project.ticketreservation.Repositories;

import com.project.ticketreservation.Models.FlightTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightTicketRepository extends JpaRepository <FlightTicket,Integer> {
    List<FlightTicket> findByPassengerNationalId(String passengerId);
}
