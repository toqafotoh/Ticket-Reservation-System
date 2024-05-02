package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.Feedback;
import com.project.ticketreservation.Repositories.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    private FeedbackRepository feedbackRepository;
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
    public Feedback getFeedbackById(Integer feedbackId) {
        return feedbackRepository.findById(feedbackId).orElseThrow(() -> new EntityNotFoundException("Feedback not found"));
    }

}
