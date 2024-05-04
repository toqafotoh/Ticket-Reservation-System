package com.project.ticketreservation.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "Flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public class Flight {
    public enum FlightClass {
        Business,Economy
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
    @Column(name = "class", length =20)
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
    @JsonIgnoreProperties("flight")
    @OneToMany(mappedBy = "flight",cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE}, orphanRemoval = true)
    private Collection<FlightTicket> tickets;
}

