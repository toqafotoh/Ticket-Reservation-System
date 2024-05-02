package com.project.ticketreservation.Repositories;

import com.project.ticketreservation.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
