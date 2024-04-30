package com.project.ticketreservation.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "round_trip_flight")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoundTripFlight {
    @Id
    @Column(name = "returning_flight_number", length = 10)
    private String flightNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oneway_flight_number",referencedColumnName = "flight_number")
    private Flight flight;
    @Column(name = "flight_start_time")
    private LocalDateTime flightStartTime;
    @Column(name = "flight_end_time")
    private LocalDateTime flightEndTime;
}
