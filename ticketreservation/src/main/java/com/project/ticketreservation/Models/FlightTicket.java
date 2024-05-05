package com.project.ticketreservation.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FlightTicket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightTicket extends Ticket {
    private String firstName ;
    private String lastName ;
    private String nationalID ;
    @Column(name = "QRCode")
    private String qrCode;
    @Column(name = "Seat")
    private String seat;
    @Column(name = "flight_number")
    private String flightNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_number",insertable=false, updatable=false)
    private Flight flight;
    @Column(name = "origin")
    private String origin ;
    @Column(name = "dest")
    private String dest ;


    public FlightTicket(String firstName, String lastName, String nationalID , String seat , String flightNumber , String origin , String dest) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalID = nationalID;
        this.seat = seat;
        this.flightNumber = flightNumber;
        this.origin = origin ;
        this.dest = dest;
    }

    public void setQRcode(String QRcode) {
        this.qrCode = QRcode;
    }


}
