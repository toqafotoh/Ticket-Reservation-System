package com.project.ticketreservation.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class EntertainmentTicket extends Ticket {

    @Column(name = "entertainment_id")
    private Integer entertainmentTourId;
    @ManyToOne
    @JoinColumn(name = "entertainment_id", referencedColumnName = "entertainment_tour_id", insertable = false, updatable = false)
    private Entertainment entertainment;

    public EntertainmentTicket(Double price, Integer entertainmentTourId, Entertainment entertainment) {
        super(price);
        this.entertainmentTourId = entertainmentTourId;
        this.entertainment = entertainment;
    }

    public EntertainmentTicket(Integer entertainmentTourId, Entertainment entertainment) {
        this.entertainmentTourId = entertainmentTourId;
        this.entertainment = entertainment;
    }

    public EntertainmentTicket(Integer ticketId, Double price, String paymentId, String passengerId,
            Passenger passenger, PaymentModel payment, Integer entertainmentTourId, Entertainment entertainment) {
        super(ticketId, price, paymentId, passengerId, passenger, payment);
        this.entertainmentTourId = entertainmentTourId;
        this.entertainment = entertainment;
    }

    public Integer getEntertainmentTourId() {
        return entertainmentTourId;
    }

    public void setEntertainmentTourId(Integer entertainmentTourId) {
        this.entertainmentTourId = entertainmentTourId;
    }

    public Entertainment getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(Entertainment entertainment) {
        this.entertainment = entertainment;
    }
}
