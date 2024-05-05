package com.project.ticketreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.models.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {
}
