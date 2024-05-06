package com.project.ticketreservation.Services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.Models.RoundTripFlight;
import com.project.ticketreservation.Repositories.FlightRepository;
import com.project.ticketreservation.Repositories.RoundTripRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoundTripFlightService {

    @Autowired
    private RoundTripRepository roundTripRepository;
    @Autowired
    private FlightRepository flightRepository;

    public boolean addRoundTripFlight(RoundTripFlight roundTripFlight) {
        if (flightRepository.existsById(roundTripFlight.getFlightNumber())) {
            return false;
        }
        roundTripRepository.save(roundTripFlight);
        return true;
    }

    public RoundTripFlight editRoundTripFlight(String roundTripId, RoundTripFlight newRoundTripDetails) {
        RoundTripFlight existingRoundTrip = roundTripRepository.findById(roundTripId)
                .orElseThrow(() -> new EntityNotFoundException("RoundTripFlight not found with ID: " + roundTripId));

        if (!existingRoundTrip.getFlightNumber().equals(newRoundTripDetails.getFlightNumber())) {
            throw new IllegalArgumentException("Cannot change the flight number of a RoundTripFlight");
        }

        existingRoundTrip.setFlightNumber(newRoundTripDetails.getFlightNumber());
        existingRoundTrip.setFlightImage(newRoundTripDetails.getFlightImage());
        existingRoundTrip.setFlightType(newRoundTripDetails.getFlightType());
        existingRoundTrip.setAirline(newRoundTripDetails.getAirline());
        existingRoundTrip.setAvailableSeats(newRoundTripDetails.getAvailableSeats());
        existingRoundTrip.setFlightStartTime(newRoundTripDetails.getFlightStartTime());
        existingRoundTrip.setFlightEndTime(newRoundTripDetails.getFlightEndTime());
        existingRoundTrip.setOrigin(newRoundTripDetails.getOrigin());
        existingRoundTrip.setDestination(newRoundTripDetails.getDestination());
        existingRoundTrip.setPrice(newRoundTripDetails.getPrice());
        existingRoundTrip.setReturnFlightEndTime(newRoundTripDetails.getReturnFlightEndTime());
        existingRoundTrip.setReturnFlightStartTime(newRoundTripDetails.getReturnFlightStartTime());

        return roundTripRepository.save(existingRoundTrip);
    }

    public List<RoundTripFlight> getAllRoundTripFlights() {
        return roundTripRepository.findAll();
    }

    public RoundTripFlight getRoundTripFlightById(String roundTripId) {
        return roundTripRepository.findById(roundTripId)
                .orElseThrow(() -> new EntityNotFoundException("RoundTripFlight not found with ID: " + roundTripId));
    }

    public boolean deleteRoundTripFlight(String roundTripId) {
        if (roundTripRepository.existsById(roundTripId)) {
            roundTripRepository.deleteById(roundTripId);
            return true;
        }
        return false;
    }

    public List<RoundTripFlight> getSimilarRoundTripFlights(RoundTripFlight roundTripFlight) {
        List<RoundTripFlight> similarFlights = roundTripRepository
                .findByFlightStartTimeBetweenAndFlightTypeAndFlightClassAndOriginAndDestination(
                        roundTripFlight.getFlightStartTime(),
                        roundTripFlight.getFlightEndTime(),
                        roundTripFlight.getFlightType(),
                        roundTripFlight.getFlightClass(),
                        roundTripFlight.getOrigin(),
                        roundTripFlight.getDestination());
        return similarFlights != null ? similarFlights : Collections.emptyList();
    }

}
