package com.project.ticketreservation.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entertainment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entertainment_tour_id")
    private Integer entertainmentTourId;
    private String description;
    private LocalDateTime time;
    private Double price;
    private String destination;
}
