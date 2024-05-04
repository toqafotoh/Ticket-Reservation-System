package com.project.ticketreservation.Models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;
    private Double price;
    @Column(name = "payment_id")
    private Integer paymentId;
    @Column(name = "passenger_id")
    private String passengerId;
    @ManyToOne
    @JoinColumn(name = "passenger_id",insertable=false, updatable=false)
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "payment_id",insertable=false, updatable=false)
    private Payment payment;
}
