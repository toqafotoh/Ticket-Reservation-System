package com.project.ticketreservation.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    public enum FlightClass {
        A, B, C, D
    }

    public enum FlightType {
        rt, ow
    }

    @Id
    @Column(name = "flight_number", length = 10)
    private String flightNumber;
    @Column(name = "flight_type")
    @Enumerated(EnumType.STRING)
    private FlightType flightType;
    @Column(name = "class", length = 1)
    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;
    @Column(name = "origin", length = 20)
    private String origin;

    @Column(name = "destination", length = 50)
    private String destination;

    @Column(name = "flight_start_time")
    private LocalDateTime flightStartTime;
    @Column(name = "flight_end_time")
    private LocalDateTime flightEndTime;
    @Column(name = "available_seats")
    private Integer availableSeats;
    @Column(length = 50)
    private String airline;
    private Double price;
    private String flightImage;
    @OneToMany(mappedBy = "flight")
    private Set<FlightTicket> tickets;
}
