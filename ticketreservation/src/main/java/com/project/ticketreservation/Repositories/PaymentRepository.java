package com.project.ticketreservation.Repositories;

import com.project.ticketreservation.Models.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel , String> {
}
