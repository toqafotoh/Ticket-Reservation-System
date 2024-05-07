package com.project.ticketreservation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "Flights")
@Inheritance(strategy = InheritanceType.JOINED)
public class Flight {
    @Id
    @Column(name = "flight_number", length = 10)
    private String flightNumber;
    @Column(name = "flight_type")
    @Enumerated(EnumType.STRING)
    private FlightType flightType;
    @Column(name = "class", length = 20)
    @Enumerated(EnumType.STRING)
    private FlightClass flightClass;
    @Column(name = "origin", length = 20)
    private String origin;

    @Column(name = "destination", length = 50)
    private String destination;
    @Future(message = "Flight start time must be in the future")
    @Column(name = "flight_start_time")
    private LocalDateTime flightStartTime;
    @Future(message = "Flight end time must be in the future")
    @Column(name = "flight_end_time")
    private LocalDateTime flightEndTime;
    @Column(name = "available_seats")
    private Integer availableSeats;
    @Column(length = 50)
    private String airline;
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;
    private String flightImage;
    @JsonIgnore
    @OneToMany(mappedBy = "flight", cascade = { CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.REMOVE }, orphanRemoval = true)
    private Collection<FlightTicket> tickets;

    public Flight() {
    }

    public Flight(String flightNumber, FlightType flightType, FlightClass flightClass, String origin,
            String destination,
            @Future(message = "Flight start time must be in the future") LocalDateTime flightStartTime,
            @Future(message = "Flight end time must be in the future") LocalDateTime flightEndTime,
            Integer availableSeats, String airline,
            @Min(value = 0, message = "Price must be greater than or equal to 0") Double price, String flightImage,
            Collection<FlightTicket> tickets) {
        this.flightNumber = flightNumber;
        this.flightType = flightType;
        this.flightClass = flightClass;
        this.origin = origin;
        this.destination = destination;
        this.flightStartTime = flightStartTime;
        this.flightEndTime = flightEndTime;
        this.availableSeats = availableSeats;
        this.airline = airline;
        this.price = price;
        this.flightImage = flightImage;
        this.tickets = tickets;
    }

    public Flight( String origin, String destination,
        @Future(message = "Flight start time must be in the future") LocalDateTime flightStartTime,
        @Future(message = "Flight end time must be in the future") LocalDateTime flightEndTime, 
        FlightType flightType, FlightClass flightClass,
        Integer availableSeats){
            this.origin = origin;
            this.destination = destination;
            this.flightStartTime = flightStartTime;
            this.flightEndTime = flightEndTime;
            this.flightType = flightType;
            this.flightClass = flightClass;
            this.availableSeats = availableSeats;
        }

    

    public enum FlightClass {
        Business, Economy
    }

    public enum FlightType {
        rt, ow
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getFlightStartTime() {
        return flightStartTime;
    }

    public void setFlightStartTime(LocalDateTime flightStartTime) {
        this.flightStartTime = flightStartTime;
    }

    public LocalDateTime getFlightEndTime() {
        return flightEndTime;
    }

    public void setFlightEndTime(LocalDateTime flightEndTime) {
        this.flightEndTime = flightEndTime;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getFlightImage() {
        return flightImage;
    }

    public void setFlightImage(String flightImage) {
        this.flightImage = flightImage;
    }

    public Collection<FlightTicket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<FlightTicket> tickets) {
        this.tickets = tickets;
    }
}
