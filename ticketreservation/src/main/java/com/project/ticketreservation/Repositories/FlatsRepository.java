package com.project.ticketreservation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ticketreservation.Models.Flat;

public interface FlatsRepository extends JpaRepository<Flat, Integer> {

}
