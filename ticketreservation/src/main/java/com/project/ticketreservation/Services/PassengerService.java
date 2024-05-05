package com.project.ticketreservation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ticketreservation.models.Passenger;
import com.project.ticketreservation.repositories.FeedbackRepository;
import com.project.ticketreservation.repositories.PassengerRepository;
import com.project.ticketreservation.repositories.PaymentRepository;

import jakarta.persistence.EntityNotFoundException;

@Transactional
@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerById(String passengerId) {
        return passengerRepository.findById(passengerId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with ID: " + passengerId));
    }

    public boolean deletePassenger(String accountId) {
        if (passengerRepository.existsById(accountId)) {
            passengerRepository.deleteById(accountId);
            return true;
        } else {
            return false;
        }
    }
}
