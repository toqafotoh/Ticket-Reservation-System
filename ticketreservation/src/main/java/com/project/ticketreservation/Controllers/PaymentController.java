package com.project.ticketreservation.Controllers;

import com.project.ticketreservation.Models.PaymentModel;
import com.project.ticketreservation.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/pay")
    public PaymentModel createPayment(PaymentModel payment){
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
