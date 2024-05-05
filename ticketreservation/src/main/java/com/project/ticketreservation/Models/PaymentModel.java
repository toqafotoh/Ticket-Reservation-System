package com.project.ticketreservation.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentModel {
    @Id
    @Column(name = "payment_id")
    private String paymentId; //insertable not incremental

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id",insertable=false, updatable=false)
    private Passenger passenger;
    
    @Column(name = "total_amount")
    private Double totalAmount;
    private Date transactionDate;
    private boolean transactionState ;


    public PaymentModel(String paymentId ,Double TotalPayment , boolean transactionState){
        this.paymentId = paymentId;
        this.totalAmount =  TotalPayment ;
        this.transactionState = transactionState ;
        this.transactionDate = new Date();
   }
}
