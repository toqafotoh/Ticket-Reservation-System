package com.project.ticketreservation.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.Models.Entertainment;

@Repository
public interface EntertainmentRepository extends JpaRepository<Entertainment, Integer> {
    List<Entertainment> findByDestination(String destination);
}
