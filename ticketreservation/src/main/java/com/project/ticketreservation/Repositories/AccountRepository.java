package com.project.ticketreservation.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.Models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByEmail(String email);

    List<Account> findByRoleIn(List<String> asList);
}
