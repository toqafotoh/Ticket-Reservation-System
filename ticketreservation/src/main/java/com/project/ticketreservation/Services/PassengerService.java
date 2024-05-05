package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.Passenger;
import com.project.ticketreservation.Repositories.FeedbackRepository;
import com.project.ticketreservation.Repositories.PassengerRepository;
import com.project.ticketreservation.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        return passengerRepository.findById(passengerId).orElseThrow(() -> new EntityNotFoundException("Passenger not found with ID: " + passengerId));
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
