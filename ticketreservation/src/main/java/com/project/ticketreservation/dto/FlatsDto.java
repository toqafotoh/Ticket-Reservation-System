package com.project.ticketreservation.dto;

import com.project.ticketreservation.models.Flat;

public class FlatsDto {
    private Integer flatId;
    private String address;
    private String flatDescription;
    private String countryName;
    private Integer capacity;
    private Double price;
    private String flatImage;

    public FlatsDto() {
    }

    public FlatsDto(Integer flatId, String address, String flatDescription, String countryName, Integer capacity,
            Double price, String flatImage) {
        this.flatId = flatId;
        this.address = address;
        this.flatDescription = flatDescription;
        this.countryName = countryName;
        this.capacity = capacity;
        this.price = price;
        this.flatImage = flatImage;
    }

    public FlatsDto(Flat flat){
        this.flatId = flat.getFlatId();
        this.address = flat.getAddress();
        this.flatDescription = flat.getFlatDescription();
        this.countryName = flat.getCountryName();
        this.capacity = flat.getCapacity();
        this.price = flat.getPrice();
        this.flatImage = flat.getFlatImage();
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

    public String getFlatImage() {
        return flatImage;
    }

    public void setFlatImage(String flatImage) {
        this.flatImage = flatImage;
    }
}
