package com.project.ticketreservation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketreservation.models.PaymentModel;
import com.project.ticketreservation.services.PaymentService;

@RestController
@RequestMapping
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public PaymentModel createPayment(PaymentModel payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/payments/total")
    public double sumTotalPayments() {
        return paymentService.sumTotalPayments();
    }

    @GetMapping("/payments")
    public List<PaymentModel> getAllPayments() {
        return paymentService.getAllPayments();
    }

}
