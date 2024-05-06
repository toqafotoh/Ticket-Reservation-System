package com.project.ticketreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.models.Flat;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Integer> {
}
