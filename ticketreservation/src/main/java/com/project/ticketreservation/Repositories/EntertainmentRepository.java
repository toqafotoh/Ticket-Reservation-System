package com.project.ticketreservation.Repositories;

import com.project.ticketreservation.Models.Entertainment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface EntertainmentRepository extends JpaRepository<Entertainment,Integer>{
    List<Entertainment> findByDestination(String destination);
}
