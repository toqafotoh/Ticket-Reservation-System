package com.project.ticketreservation.Controllers;

import com.project.ticketreservation.Models.RoundTripFlight;
import com.project.ticketreservation.Services.RoundTripFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
public class RoundTripFlightController {
    @Autowired
    private RoundTripFlightService roundTripFlightService;

    @GetMapping("/round-trip-flights")
    public List<RoundTripFlight> getAllRoundTripFlights() {
        return roundTripFlightService.getAllRoundTripFlights();
    }

    @PostMapping("/round-trip-flights")
    public boolean addRoundTripFlight(@RequestBody RoundTripFlight roundTripFlight) {
        return roundTripFlightService.addRoundTripFlight(roundTripFlight);
    }

    @GetMapping("/round-trip-flights/{id}")
    public RoundTripFlight getRoundTripFlightById(@PathVariable String id) {
        return roundTripFlightService.getRoundTripFlightById(id);
    }

    @PutMapping("/round-trip-flights/{id}")
    public RoundTripFlight updateRoundTripFlight(@PathVariable String id, @RequestBody RoundTripFlight roundTripFlightDetails) {
        return roundTripFlightService.editRoundTripFlight(id, roundTripFlightDetails);
    }

    @DeleteMapping("/round-trip-flights/{id}")
    public boolean deleteRoundTripFlight(@PathVariable String id) {
        return roundTripFlightService.deleteRoundTripFlight(id);
    }
}
