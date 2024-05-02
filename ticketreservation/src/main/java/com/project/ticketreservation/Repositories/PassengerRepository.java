package com.project.ticketreservation.Repositories;

import com.project.ticketreservation.Models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PassengerRepository extends JpaRepository<Passenger,String> {
}
