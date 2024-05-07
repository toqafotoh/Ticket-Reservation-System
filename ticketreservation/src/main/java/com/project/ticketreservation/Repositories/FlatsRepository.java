package com.project.ticketreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ticketreservation.models.Flat;

public interface FlatsRepository extends JpaRepository<Flat, Integer> {

}
