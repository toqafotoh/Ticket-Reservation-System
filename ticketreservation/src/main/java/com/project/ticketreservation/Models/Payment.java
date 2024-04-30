package com.project.ticketreservation.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id",insertable=false, updatable=false)
    private Passenger passenger;
    @Column(name = "total_amount")
    private Integer totalAmount;
    private LocalDateTime transactionDate;
}
