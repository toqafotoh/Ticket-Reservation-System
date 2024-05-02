package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.Payment;
import com.project.ticketreservation.Repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
