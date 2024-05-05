package com.project.ticketreservation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.models.Entertainment;

@Repository
public interface EntertainmentRepository extends JpaRepository<Entertainment, Integer> {
    List<Entertainment> findByDestination(String destination);
}
