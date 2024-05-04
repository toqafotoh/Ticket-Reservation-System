package com.project.ticketreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

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
}
