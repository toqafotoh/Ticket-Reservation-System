package com.project.ticketreservation.Repositories;

import com.project.ticketreservation.Models.FlatOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FlatOwnerRepository extends JpaRepository<FlatOwner,String> {
}
