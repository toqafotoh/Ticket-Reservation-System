package com.project.ticketreservation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketreservation.models.Passenger;
import com.project.ticketreservation.services.PassengerService;

@RestController
@RequestMapping
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/passengers/{id}")
    public Passenger getPassengerById(@PathVariable String id) {
        return passengerService.getPassengerById(id);
    }

    @DeleteMapping("/passengers/{id}")
    public boolean deletePassenger(@PathVariable String id) {
        return passengerService.deletePassenger(id);
    }
}
