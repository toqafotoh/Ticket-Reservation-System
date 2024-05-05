package com.project.ticketreservation.Controllers;

import com.project.ticketreservation.Models.Flight;
import com.project.ticketreservation.Services.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
