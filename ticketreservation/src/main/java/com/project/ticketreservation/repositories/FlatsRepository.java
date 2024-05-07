package com.project.ticketreservation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ticketreservation.models.Flat;


public interface FlatsRepository extends JpaRepository<Flat, Integer> {
    List<Flat> findByFlatOwnerId(String flatOwnerId);
}
