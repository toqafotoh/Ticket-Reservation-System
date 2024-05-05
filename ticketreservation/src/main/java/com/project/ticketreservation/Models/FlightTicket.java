package com.project.ticketreservation.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "FlightTicket")
public class FlightTicket extends Ticket {
    private String firstName;
    private String lastName;
    private String nationalID;
    @Column(name = "QRCode")
    private String qrCode;
    @Column(name = "Seat")
    private String seat;
    @Column(name = "flight_number")
    private String flightNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_number", insertable = false, updatable = false)
    private Flight flight;
    @Column(name = "origin")
    private String origin;
    @Column(name = "dest")
    private String dest;

    public FlightTicket() {
    }

    public FlightTicket(String firstName, String lastName, String nationalID, String qrCode, String seat,
            String flightNumber, Flight flight, String origin, String dest) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.qrCode = qrCode;
        this.seat = seat;
        this.flightNumber = flightNumber;
        this.flight = flight;
        this.origin = origin;
        this.dest = dest;
    }

    public FlightTicket(Double price, String firstName, String lastName, String nationalID, String qrCode, String seat,
            String flightNumber, Flight flight, String origin, String dest) {
        super(price);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.qrCode = qrCode;
        this.seat = seat;
        this.flightNumber = flightNumber;
        this.flight = flight;
        this.origin = origin;
        this.dest = dest;
    }

    public FlightTicket(Integer ticketId, Double price, String paymentId, String passengerId, Passenger passenger,
            PaymentModel payment, String firstName, String lastName, String nationalID, String qrCode, String seat,
            String flightNumber, Flight flight, String origin, String dest) {
        super(ticketId, price, paymentId, passengerId, passenger, payment);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.qrCode = qrCode;
        this.seat = seat;
        this.flightNumber = flightNumber;
        this.flight = flight;
        this.origin = origin;
        this.dest = dest;
    }

    public FlightTicket(String firstName, String lastName, String nationalID, String seat, String flightNumber,
            String origin, String dest) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.seat = seat;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.dest = dest;
    }

    public void setQRcode(String QRcode) {
        this.qrCode = QRcode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }
}
