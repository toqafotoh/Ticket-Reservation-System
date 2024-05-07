package com.project.ticketreservation.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.models.Account;
// import com.project.ticketreservation.models.Flat;
// import com.project.ticketreservation.models.FlatOwner;
import com.project.ticketreservation.repositories.AccountRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AccountService implements UserDetailsService {

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
        Account existingEmailAccount = accountRepository.findByEmail(newAccountData.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
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

    @Override
    public UserDetails loadUserByUsername(String nationalId) throws UsernameNotFoundException {
        return accountRepository.findByNationalId(nationalId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Account save(Account newAccount) {
        System.out.println("Saving Account");
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setUpdatedAt(LocalDateTime.now());
        return accountRepository.save(newAccount);
    }
}
