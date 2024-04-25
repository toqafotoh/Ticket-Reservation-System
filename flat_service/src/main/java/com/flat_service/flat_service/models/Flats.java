package com.flat_service.flat_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "flats")
public class Flats {
    @Id
    public String flats;
}
