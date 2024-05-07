package com.project.ticketreservation.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.dto.LoginBody;
import com.project.ticketreservation.dto.SignupBody;
import com.project.ticketreservation.models.Account;
import com.project.ticketreservation.models.FlatOwner;
import com.project.ticketreservation.models.Passenger;
import com.project.ticketreservation.models.Account.Role;
import com.project.ticketreservation.repositories.AccountRepository;
import com.project.ticketreservation.repositories.PassengerRepository;

@Service
public class AuthService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails signup(SignupBody account) {
        UserDetails newUser = null;
        if (validateEmail(account.getEmail())) {
            if(account.getRole() == Role.ADMIN){
                Account newAccount = new Account(account);
                newUser = accountService.save(newAccount);
            }
            else if(account.getRole() == Role.PASSENGER){
                Passenger newAccount = new Passenger(account);
                newUser = accountService.save(newAccount);
            }
            else{
                FlatOwner newAccount = new FlatOwner(account);
                newUser = accountService.save(newAccount);
            }
        }
        return newUser;
    }

    public String[] login(LoginBody request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email, request.hashedPassword));
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        Account account = accountRepository.findByEmail(request.email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        if (!passwordEncoder.matches(request.hashedPassword, account.getHashedPassword())) {
            throw new IllegalArgumentException("Invalid email or password.");
        }

        String token = jwtService.generateToken(account);
        return new String[] {token, jwtService.extractRole(token)};
    }

    private boolean validateEmail(String email) {
        Pattern reg = Pattern.compile("^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$");
        Matcher matcher = reg.matcher(email);
        return matcher.matches();
    }

    public boolean validateToken(String token) {
        return jwtService.isTokenValid(token);
    }

    public String extractEmail(String token) {
        return jwtService.extractEmail(token);
    }

    public String extractName(String token) {
        return jwtService.extractName(token);
    }

    public String extractRole(String token) {
        return jwtService.extractRole(token);
    }

    public String extractImage(String token) {
        return jwtService.extractImage(token);
    }

    public String extractNationality(String token) {
        return jwtService.extractNationality(token);
    }

    public String extractNationalId(String token) {
        return jwtService.extractNationalId(token);
    }

    public String extractGender(String token) {
        return jwtService.extractGender(token);
    }

    public int extractLoyalityPoints(String token) {
        return jwtService.extractLoyalityPoints(token);
    }

    public Passenger returnPassenger(String nationalId) {
        return passengerRepository.findById(nationalId).orElseThrow();
    }
}
