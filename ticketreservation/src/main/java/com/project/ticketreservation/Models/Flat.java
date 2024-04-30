package com.project.ticketreservation.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Flats")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flat_id")
    private Integer flatId;
    private String address;
    private String flatDescription;
    private String countryName;
    private Integer capacity;
    private Double price;
    @Column(name = "flat_owner_id")
    private String flatOwnerId;
    @ManyToOne
    @JoinColumn(name = "flat_owner_id",insertable = false, updatable = false)
    // Test insertable, updatable conditions inside the database
    // make sure to change the flats owner when the owner id changes "inside the component"
    private FlatOwner flatOwner;
    @Column(name = "flat_image")
    private String flatImage;
}
