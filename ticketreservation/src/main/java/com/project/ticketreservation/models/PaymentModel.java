package com.project.ticketreservation.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PaymentModel {
    @Id
    @Column(name = "payment_id")
    private String paymentId; // insertable not incremental

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", insertable = false, updatable = false)
    private Passenger passenger;

    @Column(name = "total_amount")
    private Double totalAmount;
    private Date transactionDate;
    private boolean transactionState;

    public PaymentModel(String paymentId, Double TotalPayment, boolean transactionState) {
        this.paymentId = paymentId;
        this.totalAmount = TotalPayment;
        this.transactionState = transactionState;
        this.transactionDate = new Date();
    }

    public PaymentModel() {
    }

    public PaymentModel(String paymentId, Passenger passenger, Double totalAmount, Date transactionDate,
            boolean transactionState) {
        this.paymentId = paymentId;
        this.passenger = passenger;
        this.totalAmount = totalAmount;
        this.transactionDate = transactionDate;
        this.transactionState = transactionState;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public boolean isTransactionState() {
        return transactionState;
    }

    public void setTransactionState(boolean transactionState) {
        this.transactionState = transactionState;
    }
}
