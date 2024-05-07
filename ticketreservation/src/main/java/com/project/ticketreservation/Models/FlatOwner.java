package com.project.ticketreservation.models;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.ticketreservation.dto.SignupBody;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class FlatOwner extends Passenger {
    @Column(name = "flat_owner_rate", columnDefinition = "INT default 0")
    private int flatOwnerRate;
    @JsonIgnoreProperties("flatOwner")
    @OneToMany(mappedBy = "flatOwner", cascade = { CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH, CascadeType.REMOVE }, orphanRemoval = true)
    private Collection<Flat> flats;

    public FlatOwner() {
    }

    public FlatOwner(SignupBody body) {
        super(body);
        this.flatOwnerRate = 0;
    }

    public int getFlatOwnerRate() {
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
