package com.project.ticketreservation.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlatOwner extends Passenger {
    @Column(name = "phone_num")
    // this field must have a validation
    //  [+] [country code] [subscriber number including area code] E.164 format
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be in E.164 format")
    private String phoneNum;
    @Column(name = "flat_owner_rate")
    private Integer flatOwnerRate;
    @JsonIgnoreProperties("flatOwner")
    @OneToMany(mappedBy = "flatOwner",cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE}, orphanRemoval = true)
    private Collection<Flat> flats;
}
