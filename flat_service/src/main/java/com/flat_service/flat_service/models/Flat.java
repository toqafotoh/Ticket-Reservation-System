package com.flat_service.flat_service.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "flats")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flat_id;
    private String address;
    private String description;
    private String country_name;
    private String capacity;
    private double price;
    private String ImagePath;
}
