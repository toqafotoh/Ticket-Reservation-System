package com.project.ticketreservation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Flats")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flat_id")
    private Integer flatId;
    private String address;
    private String flatDescription;
    private String countryName;
    private Integer capacity;
    private Double price;
    @Column(name = "flat_owner_id")
    private String flatOwnerId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "flat_owner_id",insertable = false, updatable = false)
    // Test insertable, updatable conditions inside the database
    // make sure to change the flats owner when the owner id changes "inside the component"
    private FlatOwner flatOwner;
    @Column(name = "flat_image")
    private String flatImage;

    public Flat() {
    }    

    public Flat(Integer flatId, String address, String flatDescription, String countryName, Integer capacity,
            Double price, String flatOwnerId, FlatOwner flatOwner, String flatImage) {
        this.flatId = flatId;
        this.address = address;
        this.flatDescription = flatDescription;
        this.countryName = countryName;
        this.capacity = capacity;
        this.price = price;
        this.flatOwnerId = flatOwnerId;
        this.flatOwner = flatOwner;
        this.flatImage = flatImage;
    }

    public Integer getFlatId() {
        return flatId;
    }
    public void setFlatId(Integer flatId) {
        this.flatId = flatId;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getFlatDescription() {
        return flatDescription;
    }
    public void setFlatDescription(String flatDescription) {
        this.flatDescription = flatDescription;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public Integer getCapacity() {
        return capacity;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getFlatOwnerId() {
        return flatOwnerId;
    }
    public void setFlatOwnerId(String flatOwnerId) {
        this.flatOwnerId = flatOwnerId;
    }
    public FlatOwner getFlatOwner() {
        return flatOwner;
    }
    public void setFlatOwner(FlatOwner flatOwner) {
        this.flatOwner = flatOwner;
    }
    public String getFlatImage() {
        return flatImage;
    }
    public void setFlatImage(String flatImage) {
        this.flatImage = flatImage;
    }

    
}
