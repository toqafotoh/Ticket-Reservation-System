package com.project.ticketreservation.dto;

public class FlatDtoRespone {
    private Integer flatId;
    private String address;
    private String flatDescription;
    private String countryName;
    private Integer capacity;
    private Double price;
    private String flatOwnerId;
    private String flatOwnerPhoneNum;
    private Integer flatOwnerRate;
    private String flatImage;

    public FlatDtoRespone() {
    }

    public FlatDtoRespone(Integer flatId, String address, String flatDescription, String countryName, Integer capacity,
            Double price, String flatOwnerId, String flatOwnerPhoneNum, Integer flatOwnerRate, String flatImage) {
        this.flatId = flatId;
        this.address = address;
        this.flatDescription = flatDescription;
        this.countryName = countryName;
        this.capacity = capacity;
        this.price = price;
        this.flatOwnerId = flatOwnerId;
        this.flatOwnerPhoneNum = flatOwnerPhoneNum;
        this.flatOwnerRate = flatOwnerRate;
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

    public String getFlatOwnerPhoneNum() {
        return flatOwnerPhoneNum;
    }

    public void setFlatOwnerPhoneNum(String flatOwnerPhoneNum) {
        this.flatOwnerPhoneNum = flatOwnerPhoneNum;
    }

    public Integer getFlatOwnerRate() {
        return flatOwnerRate;
    }

    public void setFlatOwnerRate(Integer flatOwnerRate) {
        this.flatOwnerRate = flatOwnerRate;
    }

    public String getFlatImage() {
        return flatImage;
    }

    public void setFlatImage(String flatImage) {
        this.flatImage = flatImage;
    }
}
