package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.FlatOwner;
import com.project.ticketreservation.Models.Passenger;
import com.project.ticketreservation.Repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public void updateLoyalityPoints(String passengerId, Integer points) {
        Passenger existingPassenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with ID: " + passengerId));

        existingPassenger.setLoyaltyPoints(points);
        passengerRepository.save(existingPassenger);
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerById(String passengerId) {
        return passengerRepository.findById(passengerId).orElseThrow(() -> new EntityNotFoundException("Passenger not found with ID: " + passengerId));
    }
    public boolean deletePassenger(String passengerId) {
        if (passengerRepository.existsById(passengerId)) {
            passengerRepository.deleteById(passengerId);
            return true;
        } else {
            return false;
        }
    }
}
