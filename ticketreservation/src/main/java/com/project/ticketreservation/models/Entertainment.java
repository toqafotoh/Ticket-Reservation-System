package com.project.ticketreservation.models;

import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Entertainment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entertainment_tour_id")
    private Integer entertainmentTourId;
    private String description;
    private LocalDateTime time;
    // @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;
    private String destination;
    @JsonIgnore
    @OneToMany(mappedBy = "entertainment", cascade = { CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.REMOVE }, orphanRemoval = true)
    private Collection<EntertainmentTicket> tickets;

    public Entertainment() {
    }

    public Entertainment(Integer entertainmentTourId, String description, LocalDateTime time, Double price,
            String destination, Collection<EntertainmentTicket> tickets) {
        this.entertainmentTourId = entertainmentTourId;
        this.description = description;
        this.time = time;
        this.price = price;
        this.destination = destination;
        this.tickets = tickets;
    }

    public Integer getEntertainmentTourId() {
        return entertainmentTourId;
    }

    public void setEntertainmentTourId(Integer entertainmentTourId) {
        this.entertainmentTourId = entertainmentTourId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Collection<EntertainmentTicket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<EntertainmentTicket> tickets) {
        this.tickets = tickets;
    }
}
