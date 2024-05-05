package com.project.ticketreservation.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;
    private Double price;
    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "passenger_id")
    private String passengerId;
    @ManyToOne
    @JoinColumn(name = "passenger_id", insertable = false, updatable = false)
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "payment_id", insertable = false, updatable = false)
    private PaymentModel payment;

    public Ticket() {
    }

    public Ticket(Double price) {
        this.price = null;
    }

    public Ticket(Integer ticketId, Double price, String paymentId, String passengerId, Passenger passenger,
            PaymentModel payment) {
        this.ticketId = ticketId;
        this.price = price;
        this.paymentId = paymentId;
        this.passengerId = passengerId;
        this.passenger = passenger;
        this.payment = payment;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public PaymentModel getPayment() {
        return payment;
    }

    public void setPayment(PaymentModel payment) {
        this.payment = payment;
    }

    
}
