package com.project.ticketreservation.repositories;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.models.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByFlightStartTimeBetweenAndFlightTypeAndFlightClassAndOriginAndDestination(
            LocalDateTime startTime, LocalDateTime endTime,
            Flight.FlightType flightType, Flight.FlightClass flightClass,
            String origin, String destination);
}
