package com.project.ticketreservation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ticketreservation.Models.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
