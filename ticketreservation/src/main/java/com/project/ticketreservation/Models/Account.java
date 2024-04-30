package com.project.ticketreservation.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Account")
public class Account {
    public enum Gender {
        male, female
    }

    public enum Role {
        admin, passenger, owner
    }
    @Id
    @Column(name = "national_id",nullable = false, length = 25)
    @NotBlank(message = "National ID is required")
    private String nationalId;
    @Column(length = 50)
    @NotBlank(message = "Name is required")
    private String name;
    @Column(length = 50)
    private String nationality;
    @Min(value = 0, message = "Age must be a positive number")
    @Max(value = 100, message = "You must enter a valid age")
    private Integer age;
    @Column(nullable = false, length = 6)
    @Enumerated( EnumType.STRING)
    private Gender gender;
    @Column(unique = true,nullable = false, length = 50)
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;
    @Column(length = 64)
    @NotBlank(message = "Password is required")
//  this pattern validation must be added on the password field
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
//            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
//  ASOP "@Aspect" may handle hashing the password and checking if the entered password is the same one that had been saved as hashed
    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    private String accountImage;

//    public void setPassword(String plainTextPassword) {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        this.hashedPassword = passwordEncoder.encode(plainTextPassword);
//    }
//
//    public boolean matchesPassword(String plainTextPassword) {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.matches(plainTextPassword, this.hashedPassword);
//    }
}