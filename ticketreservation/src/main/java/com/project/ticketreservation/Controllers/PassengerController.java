package com.project.ticketreservation.Controllers;

import com.project.ticketreservation.Models.Passenger;
import com.project.ticketreservation.Services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
