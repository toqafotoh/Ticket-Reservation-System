package com.project.ticketreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.models.PaymentModel;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, String> {
}
