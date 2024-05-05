package com.project.ticketreservation.Repositories;

import com.project.ticketreservation.Models.Flight;
import com.project.ticketreservation.Models.FlightTicket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByFlightStartTimeBetweenAndFlightTypeAndFlightClassAndOriginAndDestination(
            LocalDateTime startTime, LocalDateTime endTime,
            Flight.FlightType flightType, Flight.FlightClass flightClass,
            String origin, String destination
    );

}

