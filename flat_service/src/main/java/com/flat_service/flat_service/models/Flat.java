package com.flat_service.flat_service.models;

import org.attoparser.dom.Text;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name = "flats")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String flat_id;
    private String address;
    private Text description;
    private String country_name;
    private String capacity;
    private double price;
    @Lob
    private byte[] image;
}
