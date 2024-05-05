package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.Flight;
import com.project.ticketreservation.Repositories.FlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    public boolean addFlight(Flight flight) {
        if (flightRepository.existsById(flight.getFlightNumber())) {
            return false;
        }
        flightRepository.save(flight);
        return true;
    }
    public boolean deleteFlight(String flightId) {
        if (flightRepository.existsById(flightId)) {
            flightRepository.deleteById(flightId);
            return true;
        } else {
            return false;
        }
    }
    public Flight editFlight(String flightNumber, Flight newFlightDetails) {
        Flight existingFlight = flightRepository.findById(flightNumber)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with number: " + flightNumber));
        if (!flightNumber.equals(newFlightDetails.getFlightNumber())) {
            throw new IllegalArgumentException("Cannot change the flight number");
        }
        existingFlight.setFlightNumber(newFlightDetails.getFlightNumber());
        existingFlight.setFlightImage(newFlightDetails.getFlightImage());
        existingFlight.setFlightType(newFlightDetails.getFlightType());
        existingFlight.setAirline(newFlightDetails.getAirline());
        existingFlight.setAvailableSeats(newFlightDetails.getAvailableSeats());
        existingFlight.setFlightStartTime(newFlightDetails.getFlightStartTime());
        existingFlight.setFlightEndTime(newFlightDetails.getFlightEndTime());
        existingFlight.setOrigin(newFlightDetails.getOrigin());
        existingFlight.setDestination(newFlightDetails.getDestination());
        existingFlight.setPrice(newFlightDetails.getPrice());

        return flightRepository.save(existingFlight);
    }

    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }
    public Flight getFlightById(String flightId) {
        return flightRepository.findById(flightId).orElseThrow(() -> new EntityNotFoundException("Flight not found with ID: " + flightId));
    }

    public List<Flight> getSimilarFlights(Flight flight) {
        List<Flight> similarFlights = flightRepository.findByFlightStartTimeBetweenAndFlightTypeAndFlightClassAndOriginAndDestination(
                flight.getFlightStartTime(),
                flight.getFlightEndTime(),
                flight.getFlightType(),
                flight.getFlightClass(),
                flight.getOrigin(),
                flight.getDestination()
        );

        return similarFlights != null ? similarFlights : Collections.emptyList();
    }


}
