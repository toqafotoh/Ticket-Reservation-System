package com.project.ticketreservation.dto;

public class LoginBody {

    public String email;
    public String hashedPassword;

    public LoginBody(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
    }    
}
