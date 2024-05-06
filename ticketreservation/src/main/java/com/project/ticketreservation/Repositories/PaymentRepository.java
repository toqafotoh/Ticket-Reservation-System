package com.project.ticketreservation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.Models.PaymentModel;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, String> {
}
