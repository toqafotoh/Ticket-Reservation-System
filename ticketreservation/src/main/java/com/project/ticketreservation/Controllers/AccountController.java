package com.project.ticketreservation.Controllers;

import com.project.ticketreservation.Models.Account;
import com.project.ticketreservation.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Account> updateAccount(@PathVariable("id") String oldAccountId, @RequestBody Account newAccountData) {
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
    @PutMapping("/profile/{id}")
    public Account updateProfile(@PathVariable String id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }
}
