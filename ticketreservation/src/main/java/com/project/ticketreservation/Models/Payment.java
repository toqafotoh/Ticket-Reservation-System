package com.project.ticketreservation.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;
    @Column(name = "passenger_id")
    private String passengerId;
    @ManyToOne
    @JoinColumn(name = "passenger_id",insertable=false, updatable=false)
    private Passenger passenger;
    @Column(name = "total_amount")
    private Integer totalAmount;
    private LocalDateTime transactionDate;
    @JsonIgnoreProperties("payment")
    @OneToMany(mappedBy = "payment",cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE}, orphanRemoval = true)
    private Collection<Ticket> tickets;
}
