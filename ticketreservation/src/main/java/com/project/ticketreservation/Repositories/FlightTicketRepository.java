package com.project.ticketreservation.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.Models.FlightTicket;

@Repository
public interface FlightTicketRepository extends JpaRepository<FlightTicket, Integer> {
    Optional<FlightTicket> findByNationalID(String nid);

    List<FlightTicket> findByPassengerNationalId(String passengerId);
}
