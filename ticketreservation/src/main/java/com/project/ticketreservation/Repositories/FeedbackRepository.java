package com.project.ticketreservation.Repositories;

import com.project.ticketreservation.Models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {
}
