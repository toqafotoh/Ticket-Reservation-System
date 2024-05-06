package com.project.ticketreservation.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.Models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByNationalId(String nationalId);
    Optional<Account> findByEmail(String email);

    List<Account> findByRoleIn(List<String> asList);
}
