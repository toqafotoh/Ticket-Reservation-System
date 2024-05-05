package com.project.ticketreservation.services;

import com.project.ticketreservation.models.Feedback;
import com.project.ticketreservation.repositories.FeedbackRepository;
import com.project.ticketreservation.repositories.PassengerRepository;

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
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new EntityNotFoundException("Feedback not found"));
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
