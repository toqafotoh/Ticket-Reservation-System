package com.project.ticketreservation.Controllers;

import com.project.ticketreservation.Models.Flight;
import com.project.ticketreservation.Services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @PostMapping("/addFlights")
    public boolean addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }
    @GetMapping("/getFlights")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/getFlights/{id}")
    public Flight getFlightById(@PathVariable String id) {
        return flightService.getFlightById(id);
    }

    @PutMapping("/updateFlights/{id}")
    public Flight updateFlight(@PathVariable String id, @RequestBody Flight flightDetails) {
        return flightService.editFlight(id, flightDetails);
    }

    @DeleteMapping("/deleteFlights/{id}")
    public boolean deleteFlight(@PathVariable String id) {
        return flightService.deleteFlight(id);
    }
    @GetMapping("/countFlights")
    public long countFlights() {
        return flightService.countFlights();
    }
}
