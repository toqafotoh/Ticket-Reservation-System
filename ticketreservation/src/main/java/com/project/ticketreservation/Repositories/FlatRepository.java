package com.project.ticketreservation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.Models.Flat;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Integer> {
}
