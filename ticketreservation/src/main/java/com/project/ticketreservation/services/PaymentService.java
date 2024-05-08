package com.project.ticketreservation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.models.Account;
import com.project.ticketreservation.models.PaymentModel;
import com.project.ticketreservation.models.Ticket;
import com.project.ticketreservation.repositories.FlightTicketRepository;
import com.project.ticketreservation.repositories.PassengerRepository;
import com.project.ticketreservation.repositories.PaymentRepository;
import com.project.ticketreservation.repositories.TicketRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    public FlightTicketRepository fp;
    @Autowired
    public TicketRepository tr;

    public List<PaymentModel> getAllPayments() {
        return paymentRepository.findAll();
    }

    public String createPaymmentDB(PaymentModel model) {
        paymentRepository.save(model);
        return "done";
    }

    public String setTicketPaymentIDAndprice(String paymentID, Double price) {
        // Get all the tickets with a payment_id of null
        List<Ticket> ticketsWithNullPayment = tr.findBypaymentIdAndPrice(null, null);
        
        // Update the payment_id for each of the tickets
        for (Ticket ticket : ticketsWithNullPayment) {
            ticket.setPaymentId(paymentID);
            ticket.setPrice(price);
            tr.save(ticket);
        }

        // Return a success message or redirect to another page
        return "Tickets updated with payment ID successfully";
    }

    public double sumTotalPayments() {
        List<PaymentModel> payments = paymentRepository.findAll();
        double sum = 0.0;
        for (PaymentModel payment : payments) {
            sum += payment.getTotalAmount();
        }
        return sum;
    }

    public PaymentModel createPayment(PaymentModel payment) {
        String passengerId = payment.getPassenger().getNationalId();
        if (!passengerRepository.existsById(passengerId)) {
            throw new EntityNotFoundException("Passenger with ID " + passengerId + " not found");
        }
        return paymentRepository.save(payment);
    }
}
