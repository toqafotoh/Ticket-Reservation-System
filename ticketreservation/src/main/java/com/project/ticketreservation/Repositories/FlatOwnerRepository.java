package com.project.ticketreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.models.FlatOwner;

@Repository
public interface FlatOwnerRepository extends JpaRepository<FlatOwner, String> {
}
