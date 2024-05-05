package com.project.ticketreservation.Services;

import com.project.ticketreservation.Models.Feedback;
import com.project.ticketreservation.Repositories.FeedbackRepository;
import com.project.ticketreservation.Repositories.PassengerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
    public Feedback getFeedbackById(Integer feedbackId) {
        return feedbackRepository.findById(feedbackId).orElseThrow(() -> new EntityNotFoundException("Feedback not found"));
    }
    public Feedback addFeedback(Feedback feedback) {
        if (!passengerRepository.existsById(feedback.getPassengerId())) {
            throw new EntityNotFoundException("Passenger not found with ID: " + feedback.getPassengerId());
        }
        return feedbackRepository.save(feedback);
    }
    public long countFeedbacks() {
        return feedbackRepository.count();
    }
}
