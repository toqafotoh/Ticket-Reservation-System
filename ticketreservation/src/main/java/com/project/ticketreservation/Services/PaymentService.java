package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.FlightTicket;
import com.project.ticketreservation.Models.PaymentModel;
import com.project.ticketreservation.Models.Ticket;
import com.project.ticketreservation.Repositories.FlightTicketRepository;
import com.project.ticketreservation.Repositories.PaymentRepository;
import com.project.ticketreservation.Repositories.TicketReopsitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

@Autowired
    public PaymentRepository payrepo ;

    @Autowired 
    public FlightTicketRepository fp ;
    @Autowired
    public TicketReopsitory tr ;

    public String createPaymmentDB(PaymentModel model){
        payrepo.save(model);
        return"done";
    }

    public String setTicketPaymentIDAndprice(String paymentID , Double price) {
        // Get all the tickets with a payment_id of null
        List<Ticket> ticketsWithNullPayment = tr.findBypaymentIdAndPrice(null , null);
    
        // Update the payment_id for each of the tickets
        for (Ticket ticket : ticketsWithNullPayment) {
            ticket.setPaymentId(paymentID);
            ticket.setPrice(price);
            tr.save(ticket);
        }
    
        // Return a success message or redirect to another page
        return "Tickets updated with payment ID successfully";
    }
    
    public List<PaymentModel> getAllPayments() {
        return payrepo.findAll();
    }
}
