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
public class RoundTripFlight extends Flight {
    @Column(name = "return_flight_start_time")
    private LocalDateTime returnFlightStartTime;
    @Column(name = "return_flight_end_time")
    private LocalDateTime returnFlightEndTime;
}
