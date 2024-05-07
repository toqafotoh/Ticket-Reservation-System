package com.project.ticketreservation.dto;

import com.project.ticketreservation.models.Flat;

public class FlatsProfileDto {
    private String address;
    private String flatDescription;
    private String countryName;
    private Integer capacity;
    private Double price;
    private String flatImage;

    public FlatsProfileDto() {
    }

    public FlatsProfileDto(Flat flat){
        this.address = flat.getAddress();
        this.flatDescription = flat.getFlatDescription();
        this.countryName = flat.getCountryName();
        this.capacity = flat.getCapacity();
        this.price = flat.getPrice();
        this.flatImage = flat.getFlatImage();
    }

    public FlatsProfileDto(String flatOwnerId, String address, String flatDescription, String countryName, Integer capacity, Double price,
            String flatImage) {
        this.address = address;
        this.flatDescription = flatDescription;
        this.countryName = countryName;
        this.capacity = capacity;
        this.price = price;
        this.flatImage = flatImage;
    }

    public String getAddress() {
        return address;
    }

    public String getFlatDescription() {
        return flatDescription;
    }

    public String getCountryName() {
        return countryName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Double getPrice() {
        return price;
    }

    public String getFlatImage() {
        return flatImage;
    }
}
