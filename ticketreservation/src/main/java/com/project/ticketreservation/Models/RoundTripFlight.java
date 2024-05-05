package com.project.ticketreservation.Models;

import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "round_trip_flight")
public class RoundTripFlight extends Flight {
    @Column(name = "return_flight_start_time")
    private LocalDateTime returnFlightStartTime;
    @Column(name = "return_flight_end_time")
    private LocalDateTime returnFlightEndTime;

    public RoundTripFlight(String flightNumber, FlightType flightType, FlightClass flightClass, String origin,
            String destination,
            @Future(message = "Flight start time must be in the future") LocalDateTime flightStartTime,
            @Future(message = "Flight end time must be in the future") LocalDateTime flightEndTime,
            Integer availableSeats, String airline,
            @Min(value = 0, message = "Price must be greater than or equal to 0") Double price, String flightImage,
            Collection<FlightTicket> tickets, LocalDateTime returnFlightStartTime, LocalDateTime returnFlightEndTime) {
        super(flightNumber, flightType, flightClass, origin, destination, flightStartTime, flightEndTime,
                availableSeats, airline, price, flightImage, tickets);
        this.returnFlightStartTime = returnFlightStartTime;
        this.returnFlightEndTime = returnFlightEndTime;
    }

    public RoundTripFlight(LocalDateTime returnFlightStartTime, LocalDateTime returnFlightEndTime) {
        this.returnFlightStartTime = returnFlightStartTime;
        this.returnFlightEndTime = returnFlightEndTime;
    }

    public LocalDateTime getReturnFlightStartTime() {
        return returnFlightStartTime;
    }

    public void setReturnFlightStartTime(LocalDateTime returnFlightStartTime) {
        this.returnFlightStartTime = returnFlightStartTime;
    }

    public LocalDateTime getReturnFlightEndTime() {
        return returnFlightEndTime;
    }

    public void setReturnFlightEndTime(LocalDateTime returnFlightEndTime) {
        this.returnFlightEndTime = returnFlightEndTime;
    }
}
