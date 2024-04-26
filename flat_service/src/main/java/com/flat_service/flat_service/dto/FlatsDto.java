package com.flat_service.flat_service.dto;

import org.attoparser.dom.Text;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data

public class FlatsDto {
    private String address;
    private Text description;
    private String country_name;
    private String capacity;
    private double price;
    private MultipartFile image;
}
