package com.project.ticketreservation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketreservation.models.Flight;
import com.project.ticketreservation.services.FlightService;

@RestController
@RequestMapping
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("/flights")
    public boolean addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @GetMapping("/flights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/flights/{id}")
    public Flight getFlightById(@PathVariable String id) {
        return flightService.getFlightById(id);
    }

    @PutMapping("/flights/{id}")
    public Flight updateFlight(@PathVariable String id, @RequestBody Flight flightDetails) {
        return flightService.editFlight(id, flightDetails);
    }

    @DeleteMapping("/flights/{id}")
    public boolean deleteFlight(@PathVariable String id) {
        return flightService.deleteFlight(id);
    }

    @GetMapping("/flights/count")
    public long countFlights() {
        return flightService.countFlights();
    }
}
