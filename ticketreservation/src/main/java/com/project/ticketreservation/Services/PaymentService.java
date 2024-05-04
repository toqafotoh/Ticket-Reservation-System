package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.Payment;
import com.project.ticketreservation.Repositories.PassengerRepository;
import com.project.ticketreservation.Repositories.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    public double sumTotalPayments() {
        List<Payment> payments = paymentRepository.findAll();
        double sum = 0.0;
        for (Payment payment : payments) {
            sum += payment.getTotalAmount();
        }
        return sum;
    }
    public Payment createPayment(Payment payment) {
        String passengerId = payment.getPassengerId();
        if (!passengerRepository.existsById(passengerId)) {
            throw new EntityNotFoundException("Passenger with ID " + passengerId + " not found");
        }
        return paymentRepository.save(payment);
    }
}
