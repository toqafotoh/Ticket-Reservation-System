package com.project.ticketreservation.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.models.Flight;
import com.project.ticketreservation.repositories.FlightRepository;

import jakarta.persistence.EntityNotFoundException;

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
        existingFlight.setFlightClass(newFlightDetails.getFlightClass());
        existingFlight.setAirline(newFlightDetails.getAirline());
        existingFlight.setAvailableSeats(newFlightDetails.getAvailableSeats());
        existingFlight.setFlightStartTime(newFlightDetails.getFlightStartTime());
        existingFlight.setOrigin(newFlightDetails.getOrigin());
        existingFlight.setDestination(newFlightDetails.getDestination());
        existingFlight.setPrice(newFlightDetails.getPrice());

        return flightRepository.save(existingFlight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(String flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with ID: " + flightId));
    }

    public List<Flight> getSimilarFlights(Flight flight) {
        List<Flight> similarFlights = flightRepository
                .findByFlightStartTimeBetweenAndFlightTypeAndFlightClassAndOriginAndDestination(
                        flight.getFlightStartTime(),
                        flight.getFlightEndTime(),
                        flight.getFlightType(),
                        flight.getFlightClass(),
                        flight.getOrigin(),
                        flight.getDestination());

        // return filterFlightsByAvailableSeats(similarFlights,
        // flight.getAvailableSeats());
        List<Flight> validFlights = filterFlightsByAvailableSeats(similarFlights, flight.getAvailableSeats());
        if (validFlights.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return validFlights;
    }

    // public List<Flight> getSimilarFlights(String origin , String destination , Date flightStartDate , Date flightEndDate ,Flight.FlightType flightType, Flight.FlightClass flightClass , Integer avaliableSeats) {
    //     List<Flight> similarFlights = flightRepository
    //             .findByFlightStartTimeBetweenAndFlightTypeAndFlightClassAndOriginAndDestination(
    //                     flightStartDate,
    //                     flightEndDate,
    //                     flightType,
    //                     flightClass,
    //                     origin,
    //                     destination);

    //     // return filterFlightsByAvailableSeats(similarFlights,
    //     // flight.getAvailableSeats());
    //     List<Flight> validFlights = filterFlightsByAvailableSeats(similarFlights, avaliableSeats);
    //     if (validFlights.isEmpty()) {
    //         throw new EmptyResultDataAccessException(1);
    //     }
    //     return validFlights;
    // }

    private List<Flight> filterFlightsByAvailableSeats(List<Flight> flights, int requiredSeats) {
        List<Flight> validFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getAvailableSeats() >= requiredSeats) {
                validFlights.add(flight);
            }
        }
        return validFlights;
    }

    public List<Flight> filterFlightsByPriceRange(List<Flight> flights, double minPrice, double maxPrice) {
        List<Flight> validFlights = new ArrayList<>();
        for (Flight flight : flights) {
            double price = flight.getPrice();
            if (price >= minPrice && price <= maxPrice) {
                validFlights.add(flight);
            }
        }
        return validFlights;
    }

    public long countFlights() {
        return flightRepository.count();
    }

}
