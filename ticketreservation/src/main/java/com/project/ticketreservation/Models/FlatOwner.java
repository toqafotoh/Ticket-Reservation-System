package com.project.ticketreservation.models;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;

@Entity
public class FlatOwner extends Passenger {
    @Column(name = "phone_num")
    // this field must have a validation
    // [+] [country code] [subscriber number including area code] E.164 format
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be in E.164 format")
    private String phoneNum;
    @Column(name = "flat_owner_rate")
    private Integer flatOwnerRate;
    @JsonIgnoreProperties("flatOwner")
    @OneToMany(mappedBy = "flatOwner", cascade = { CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.REMOVE }, orphanRemoval = true)
    private Collection<Flat> flats;

    public FlatOwner() {
    }

    public FlatOwner(int loyaltyPoints, Collection<Ticket> tickets, Collection<PaymentModel> payments,
            Collection<Feedback> feedbacks,
            @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be in E.164 format") String phoneNum,
            Integer flatOwnerRate, Collection<Flat> flats) {
        super(loyaltyPoints, tickets, payments, feedbacks);
        this.phoneNum = phoneNum;
        this.flatOwnerRate = flatOwnerRate;
        this.flats = flats;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getFlatOwnerRate() {
        return flatOwnerRate;
    }

    public void setFlatOwnerRate(Integer flatOwnerRate) {
        this.flatOwnerRate = flatOwnerRate;
    }

    public Collection<Flat> getFlats() {
        return flats;
    }

    public void setFlats(Collection<Flat> flats) {
        this.flats = flats;
    }
}
