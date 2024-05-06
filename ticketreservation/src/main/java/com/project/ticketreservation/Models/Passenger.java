package com.project.ticketreservation.Models;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.ticketreservation.dto.SignupBody;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Passenger extends Account {
    @Column(columnDefinition = "INT default 0")
    private int loyaltyPoints;
    @JsonIgnoreProperties("passenger")
    @OneToMany(mappedBy = "passenger", cascade = { CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.REMOVE }, orphanRemoval = true)
    private Collection<Ticket> tickets;
    @JsonIgnoreProperties("passenger")
    @OneToMany(mappedBy = "passenger", cascade = { CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.REMOVE }, orphanRemoval = true)
    private Collection<PaymentModel> payments;
    @JsonIgnoreProperties("passenger")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "passenger", orphanRemoval = true, cascade = { CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE })
    private Collection<Feedback> feedbacks;

    @PreRemove
    private void preRemove() {
        for (Feedback feedback : feedbacks) {
            feedback.setPassenger(null);
        }
        feedbacks.clear(); // Remove references to Feedback entities
    }

    public Passenger() {
    }

    public Passenger(SignupBody body){
        super(body);
        this.loyaltyPoints = 0;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Collection<PaymentModel> getPayments() {
        return payments;
    }

    public void setPayments(Collection<PaymentModel> payments) {
        this.payments = payments;
    }

    public Collection<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Collection<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
