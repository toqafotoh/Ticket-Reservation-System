package com.project.ticketreservation.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.text.ParseException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
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

    private Flight flightToSearch ;

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

    // @PostMapping("/flights/search")
    // public String recieveFlightData(@RequestBody Map<String , String> request)
    // {
    //     Date flightStartDate = Date.valueOf(request.get("flightStartDate"));
    //     Date flightEndDate = Date.valueOf(request.get("flightEndDate"));

    //     flightToSearch = new Flight(request.get("origin"), request.get("destination"),
    //       flightStartDate.toInstant()
    //         .atZone(ZoneId.systemDefault())
    //         .toLocalDateTime()
    //         , flightEndDate.toInstant()
    //         .atZone(ZoneId.systemDefault())
    //         .toLocalDateTime()
    //     , Flight.FlightType.valueOf(request.get("flightType")) , 
    //       Flight.FlightClass.valueOf(request.get("flightClass") ),Integer.valueOf( request.get("avaliableSeats")));
        
    //       return "Done";
    // }

    @PostMapping("/flights/search")
public Flight recieveFlightData(@RequestBody Map<String , String> request) {
    String flightStartDateStr = request.get("flightStartDate");
    String flightEndDateStr = request.get("flightEndDate");

    // Parse the date strings into java.sql.Date objects
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date flightStartDate = null;
    Date flightEndDate = null;
    try {
        flightStartDate = new Date(dateFormat.parse(flightStartDateStr).getTime());
        flightEndDate = new Date(dateFormat.parse(flightEndDateStr).getTime());
    } catch (ParseException e) {
        // Handle parsing exception
        e.printStackTrace();
    }

    // Convert java.sql.Date objects to LocalDateTime
    LocalDateTime flightStartDateTime = flightStartDate.toLocalDate().atStartOfDay();
    LocalDateTime flightEndDateTime = flightEndDate.toLocalDate().atStartOfDay();

    // Create the Flight object
    flightToSearch = new Flight(request.get("origin"), request.get("destination"),
            flightStartDateTime, flightEndDateTime,
            Flight.FlightType.valueOf(request.get("flightType")),
            Flight.FlightClass.valueOf(request.get("flightClass")),
            Integer.valueOf(request.get("availableSeats")));

    return flightToSearch;
}

    

    @GetMapping("/flights/result")
    public List<Flight> searchFlights()
    {
        return flightService.getSimilarFlights(flightToSearch);
    }
}
