package com.project.ticketreservation.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public class Passenger extends Account {
    @Column(columnDefinition = "INT default 0")
    private int loyaltyPoints;

    @OneToMany(mappedBy = "passenger")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "passenger")
    private Set<Feedback> feedbacks;
}
