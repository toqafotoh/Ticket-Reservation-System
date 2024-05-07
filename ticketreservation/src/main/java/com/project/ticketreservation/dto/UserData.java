package com.project.ticketreservation.dto;

import com.project.ticketreservation.models.Passenger;
import com.project.ticketreservation.models.Account.Gender;

public class UserData {

    private String name;
    private String email;
    private String nationalId;
    private String nationality;
    private Gender gender;
    private int age;
    private int loyaltyPoints;

    public UserData() {
    }

    public UserData(Passenger passenger) {
        this.name = passenger.getName();
        this.email = passenger.getEmail();
        this.nationalId = passenger.getNationalId();
        this.nationality = passenger.getNationality();
        this.gender = passenger.getGender();
        this.age = passenger.getAge();
        this.loyaltyPoints = passenger.getLoyaltyPoints();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getNationality() {
        return nationality;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}
