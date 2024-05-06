package com.project.ticketreservation.Models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Account")
public class Account implements UserDetails {

    @Id
    @Column(length = 25)
    private String nationalId;

    @Column(unique = true, nullable = false, length = 50)
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @Column(length = 64)
    // this pattern validation must be added on the password field
    // @Pattern(regexp =
    // "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
    // message = "Password must be at least 8 characters long and contain at least
    // one uppercase letter, one lowercase letter, one digit, and one special
    // character")
    // ASOP "@Aspect" may handle hashing the password and checking if the entered
    // password is the same one that had been saved as hashed
    private String hashedPassword;

    @Column(length = 50)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(length = 50)
    private String nationality;

    @Min(value = 0, message = "Age must be a positive number")
    @Max(value = 100, message = "You must enter a valid age")
    private Integer age;

    @Column(nullable = false, length = 6)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    private String accountImage;
    private String phoneNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Account() {
    }

    public Account(@NotBlank(message = "National ID is required") String nationalId,
            @NotBlank(message = "Name is required") String name, String nationality,
            @Min(value = 0, message = "Age must be a positive number") @Max(value = 100, message = "You must enter a valid age") Integer age,
            String gender,
            @NotBlank(message = "Email is required") @Email(message = "Email must be a valid email address") String email,
            String hashedPassword, String role, String accountImage, String phoneNum) {
        this.nationalId = nationalId;
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.gender = Gender.valueOf(gender);
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = Role.valueOf(role);
        this.accountImage = accountImage;
        this.phoneNum = phoneNum;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public enum Gender {
        MALE,
        FEMALE
    }

    public enum Role {
        ADMIN,
        PASSENGER,
        FLATOWNER
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}