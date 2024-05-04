package com.project.ticketreservation.dto;

import lombok.Data;

@Data

public class FlatsDto {
    private String flatOwner_id;
    private String address;
    private String description;
    private String country_name;
    private Integer capacity;
    private Double price;
    private String flat_image;
}
