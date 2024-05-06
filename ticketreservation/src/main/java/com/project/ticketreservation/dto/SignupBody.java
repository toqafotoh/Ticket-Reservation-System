package com.project.ticketreservation.dto;

import com.project.ticketreservation.models.Account.Gender;
import com.project.ticketreservation.models.Account.Role;

public class SignupBody {

    private String nationalId;
    private String email;
    private String hashedPassword;
    private String name;
    private String nationality;
    private Integer age;
    private Gender gender;
    private Role role;
    private String accountImage;
    private String phoneNum;
    
    public SignupBody() {
    }
    
    public SignupBody(String nationalId, String email, String hashedPassword, String name, String nationality,
            Integer age, Gender gender, Role role, String accountImage, String phoneNum) {
        this.nationalId = nationalId;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.accountImage = accountImage;
        this.phoneNum = phoneNum;
    }

    public String getNationalId() {
        return nationalId;
    }
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public String getAccountImage() {
        return accountImage;
    }
    public void setAccountImage(String accountImage) {
        this.accountImage = accountImage;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    
}
