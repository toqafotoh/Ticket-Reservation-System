package com.project.ticketreservation.Models;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class FlatOwner extends Passenger {
    @Column(name = "flat_owner_rate")
    private Integer flatOwnerRate;
    @JsonIgnoreProperties("flatOwner")
    @OneToMany(mappedBy = "flatOwner", cascade = { CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.REMOVE }, orphanRemoval = true)
    private Collection<Flat> flats;

    public FlatOwner() {
    }

    public FlatOwner(int loyaltyPoints, Collection<Ticket> tickets, Collection<PaymentModel> payments,
            Collection<Feedback> feedbacks,
            Integer flatOwnerRate, Collection<Flat> flats) {
        super(loyaltyPoints, tickets, payments, feedbacks);
        this.flatOwnerRate = flatOwnerRate;
        this.flats = flats;
    }

    public Integer getFlatOwnerRate() {
        return flatOwnerRate;
    }

    public void setFlatOwnerRate(Integer flatOwnerRate) {
        this.flatOwnerRate = flatOwnerRate;
    }

    public Collection<Flat> getFlats() {
        return flats;
    }

    public void setFlats(Collection<Flat> flats) {
        this.flats = flats;
    }
}
