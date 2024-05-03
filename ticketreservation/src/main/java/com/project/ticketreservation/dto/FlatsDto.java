package com.project.ticketreservation.dto;


import com.project.ticketreservation.Models.FlatOwner;


import lombok.Data;

@Data

public class FlatsDto {
    private FlatOwner flatOwner;
    private String address;
    private String description;
    private String country_name;
    private Integer capacity;
    private Double price;
    private String image;

    // private String flatImage;
}
