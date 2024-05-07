package com.project.ticketreservation.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.models.Flight;
import com.project.ticketreservation.models.RoundTripFlight;

@Repository
public interface RoundTripRepository extends JpaRepository<RoundTripFlight, String> {
        List<RoundTripFlight> findByFlightStartTimeBetweenAndFlightTypeAndFlightClassAndOriginAndDestination(
                        LocalDateTime startTime, LocalDateTime endTime,
                        Flight.FlightType flightType, Flight.FlightClass flightClass,
                        String origin, String destination);
}
