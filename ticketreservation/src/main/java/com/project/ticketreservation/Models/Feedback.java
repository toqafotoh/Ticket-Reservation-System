package com.project.ticketreservation.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Column(length = 200)
    private String content;

    @Column(name = "feedback_date")
    private LocalDate feedbackDate;
    @Min(0)
    @Max(5)
    private Integer rate;

    @Column(name = "passenger_id", length = 25)
    private String passengerId;
    @ManyToOne
    @JoinColumn(name = "passenger_id", insertable = false, updatable = false)
    private Passenger passenger;
}
