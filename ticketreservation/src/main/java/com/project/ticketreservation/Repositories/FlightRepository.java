package com.project.ticketreservation.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.Models.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByFlightStartTimeBetweenAndFlightTypeAndFlightClassAndOriginAndDestination(
            LocalDateTime startTime, LocalDateTime endTime,
            Flight.FlightType flightType, Flight.FlightClass flightClass,
            String origin, String destination);

}
