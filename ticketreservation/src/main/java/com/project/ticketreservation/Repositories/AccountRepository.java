package com.project.ticketreservation.Repositories;

import com.project.ticketreservation.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    Account findByEmail(String email);

    List<Account> findByRoleIn(List<String> asList);
}
