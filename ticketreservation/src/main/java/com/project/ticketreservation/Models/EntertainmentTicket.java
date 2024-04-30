package com.project.ticketreservation.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntertainmentTicket extends Ticket {

    @Column(name = "entertainment_id")
    private Integer entertainmentTourId;
    @ManyToOne
    @JoinColumn(name = "entertainment_id",referencedColumnName="entertainment_tour_id", insertable = false,updatable = false)
    private Entertainment entertainment;
}
