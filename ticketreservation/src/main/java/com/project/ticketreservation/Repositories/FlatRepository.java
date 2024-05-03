package com.project.ticketreservation.Repositories;

import com.project.ticketreservation.Models.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepository extends JpaRepository< Flat,Integer> {
}
