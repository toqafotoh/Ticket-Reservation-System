package com.project.ticketreservation.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.Models.PaymentModel;
import com.project.ticketreservation.Models.Ticket;
import com.project.ticketreservation.Repositories.FlightTicketRepository;
import com.project.ticketreservation.Repositories.PassengerRepository;
import com.project.ticketreservation.Repositories.PaymentRepository;
import com.project.ticketreservation.Repositories.TicketReopsitory;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    public FlightTicketRepository fp;
    @Autowired
    public TicketReopsitory tr;

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
