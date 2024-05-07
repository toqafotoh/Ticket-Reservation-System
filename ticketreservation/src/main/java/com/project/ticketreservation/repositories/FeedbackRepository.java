package com.project.ticketreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.models.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
