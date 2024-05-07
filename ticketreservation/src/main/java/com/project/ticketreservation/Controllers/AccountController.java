package com.project.ticketreservation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketreservation.dto.UpdateBody;
import com.project.ticketreservation.models.Account;
import com.project.ticketreservation.services.AccountService;

@RestController
@RequestMapping
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> getAllUsers(){
        return accountService.getAllUsers();
    }

    @PutMapping("/accounts/update")
    public ResponseEntity<?> updateAccount(@RequestBody UpdateBody updatedData) {
        Account updatedAccount = accountService.updateAccount(updatedData);
        return ResponseEntity.ok().body(updatedAccount);
    }
    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") String accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok().body(account);
    }
    @DeleteMapping("/users/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteAccount(@PathVariable String id){
        return accountService.deleteAccount(id);
    }
}
