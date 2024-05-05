package com.project.ticketreservation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.Models.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {
}
