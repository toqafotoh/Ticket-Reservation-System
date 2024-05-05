package com.project.ticketreservation.Models;
import jakarta.persistence.*;
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
    private String paymentId;
    @Column(name = "passenger_id")
    private String passengerId;
    @ManyToOne
    @JoinColumn(name = "passenger_id",insertable=false, updatable=false)
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "payment_id",insertable=false, updatable=false)
    private PaymentModel payment;
    public Ticket(Double price)
    {
        this.price = null ;

    }
}
