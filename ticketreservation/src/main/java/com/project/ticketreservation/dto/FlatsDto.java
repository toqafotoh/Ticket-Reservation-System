package com.project.ticketreservation.dto;

public class FlatsDto {
    private String flatOwner_id;
    private String address;
    private String description;
    private String country_name;
    private Integer capacity;
    private Double price;
    private String flat_image;

    public FlatsDto() {
    }

    public FlatsDto(String flatOwner_id, String address, String description, String country_name, Integer capacity,
            Double price, String flat_image) {
        this.flatOwner_id = flatOwner_id;
        this.address = address;
        this.description = description;
        this.country_name = country_name;
        this.capacity = capacity;
        this.price = price;
        this.flat_image = flat_image;
    }

    public String getFlatOwner_id() {
        return flatOwner_id;
    }

    public void setFlatOwner_id(String flatOwner_id) {
        this.flatOwner_id = flatOwner_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
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

    public String getFlat_image() {
        return flat_image;
    }

    public void setFlat_image(String flat_image) {
        this.flat_image = flat_image;
    }
}
