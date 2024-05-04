package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.Account;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ticketreservation.Repositories.AccountRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public Account updateAccount(String oldAccountId, Account newAccountData) {
        Account existingAccount = accountRepository.findById(oldAccountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with ID: " + oldAccountId));

        validateUpdateAccount(oldAccountId, newAccountData);

        setAccountProperties(existingAccount, newAccountData);

        return accountRepository.save(existingAccount);
    }
    private void setAccountProperties(Account existingAccount, Account newAccountData) {
        existingAccount.setAge(newAccountData.getAge());
        existingAccount.setName(newAccountData.getName());
        existingAccount.setGender(newAccountData.getGender());
        existingAccount.setEmail(newAccountData.getEmail());
        existingAccount.setNationality(newAccountData.getNationality());
        existingAccount.setNationalId(newAccountData.getNationalId());
    }
    private void validateUpdateAccount(String oldAccountId, Account newAccountData) {
        if (!oldAccountId.equals(newAccountData.getNationalId())) {
            throw new IllegalArgumentException("Cannot change the account ID");
        }
        Account existingEmailAccount = accountRepository.findByEmail(newAccountData.getEmail());
        if (existingEmailAccount != null && !existingEmailAccount.getNationalId().equals(oldAccountId)) {
            throw new IllegalArgumentException("Email " + newAccountData.getEmail() + " already exists");
        }
    }

    public boolean deleteAccount(String accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
            return true;
        } else {
            return false;
        }
    }
    public List<Account> getAllUsers() {
            return accountRepository.findByRoleIn(Arrays.asList("passenger", "owner"));
    }
    public Account getAccountById(String accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with ID: " + accountId));
    }

}
