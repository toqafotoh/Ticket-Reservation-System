package com.project.ticketreservation.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public class Passenger extends Account {
    @Column(columnDefinition = "INT default 0")
    private int loyaltyPoints;
    @JsonIgnoreProperties("passenger")
    @OneToMany(mappedBy = "passenger",cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE}, orphanRemoval = true)
    private Collection<Ticket> tickets;
    @JsonIgnoreProperties("passenger")
    @OneToMany(mappedBy = "passenger",cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE}, orphanRemoval = true)
    private Collection<PaymentModel> payments;
    @JsonIgnoreProperties("passenger")
    @OneToMany(fetch=FetchType.EAGER, mappedBy="passenger", orphanRemoval = true, cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
    private Collection<Feedback> feedbacks;
    @PreRemove
    private void preRemove() {
        for (Feedback feedback : feedbacks) {
            feedback.setPassenger(null);
        }
        feedbacks.clear(); // Remove references to Feedback entities
    }
}
