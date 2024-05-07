package com.project.ticketreservation.dto;

public class UpdateBody {

    private String name;
    private String email;
    private String nationality;
    private int age;
    private String password;

    public UpdateBody() {
    }

    public UpdateBody(String name, String email, String nationality, int age, String password) {
        this.name = name;
        this.email = email;
        this.nationality = nationality;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }
}
