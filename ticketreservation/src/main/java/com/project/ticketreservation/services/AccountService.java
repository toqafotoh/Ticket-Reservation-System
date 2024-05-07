package com.project.ticketreservation.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ticketreservation.dto.UpdateBody;
import com.project.ticketreservation.models.Account;
// import com.project.ticketreservation.models.Flat;
// import com.project.ticketreservation.models.FlatOwner;
import com.project.ticketreservation.repositories.AccountRepository;
import com.project.ticketreservation.security.PasswordConfig;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordConfig passwordConfig;

    public Account updateAccount(UpdateBody newAccountData) {
        Account current = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Account existingAccount = accountRepository.findById(current.getNationalId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        setAccountProperties(existingAccount, newAccountData);

        return accountRepository.save(existingAccount);
    }

    private void setAccountProperties(Account existingAccount, UpdateBody newAccountData) {
        existingAccount.setName(newAccountData.getName());
        existingAccount.setEmail(newAccountData.getEmail());
        existingAccount.setNationality(newAccountData.getNationality());
        existingAccount.setAge(newAccountData.getAge());
        existingAccount.setHashedPassword(passwordConfig.passwordEncoder().encode(newAccountData.getPassword()));
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
        return accountRepository.findByRoleIn(Arrays.asList("PASSENGER", "FLATOWNER"));
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
