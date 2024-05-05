package com.project.ticketreservation.Controllers;

import com.project.ticketreservation.Handler.GlobalExceptionHandler;
import com.project.ticketreservation.Models.Account;
import com.project.ticketreservation.Services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/users")
    public List<Account> getAllUsers(){
        return accountService.getAllUsers();
    }
    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable("id") String oldAccountId, @RequestBody @Valid Account newAccountData, BindingResult result) {
        if (result.hasErrors()) {
            return GlobalExceptionHandler.handleValidationErrors(result);
        }
        Account updatedAccount = accountService.updateAccount(oldAccountId, newAccountData);
        return ResponseEntity.ok().body(updatedAccount);
    }
    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") String accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok().body(account);
    }
    @DeleteMapping("/users/delete/{id}")
    public boolean deleteAccount(@PathVariable String id){
        return accountService.deleteAccount(id);
    }
}
