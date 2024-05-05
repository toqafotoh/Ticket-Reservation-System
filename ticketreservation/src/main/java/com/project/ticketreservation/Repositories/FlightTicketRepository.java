package com.project.ticketreservation.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.models.FlightTicket;

@Repository
public interface FlightTicketRepository extends JpaRepository<FlightTicket, Integer> {
    Optional<FlightTicket> findByNationalID(String nid);

    List<FlightTicket> findByPassengerNationalId(String passengerId);
}
