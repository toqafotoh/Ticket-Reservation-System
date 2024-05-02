package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.Account;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ticketreservation.Repositories.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account updateAccount(String oldAccountId, Account newAccountData) {
        Account existingAccount = accountRepository.findById(oldAccountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with ID: " + oldAccountId));
        if (!oldAccountId.equals(newAccountData.getNationalId())) {
            throw new IllegalArgumentException("Cannot change the account ID");
        }
        Account existingEmailAccount = accountRepository.findByEmail(newAccountData.getEmail());
        if (!existingEmailAccount.getNationalId().equals(oldAccountId) && existingEmailAccount != null) {
            throw new IllegalArgumentException("Email " + newAccountData.getEmail() + " already exists");
        }
        existingAccount.setAccountImage(newAccountData.getAccountImage());
        existingAccount.setAge(newAccountData.getAge());
        existingAccount.setName(newAccountData.getName());
        existingAccount.setHashedPassword(newAccountData.getHashedPassword());
        existingAccount.setNationality(newAccountData.getNationality());
        existingAccount.setNationalId(newAccountData.getNationalId());
        return accountRepository.save(existingAccount);
    }

    //delete account, payment and feedback get methods, inside their services
}
