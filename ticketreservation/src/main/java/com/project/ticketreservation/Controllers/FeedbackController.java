package com.project.ticketreservation.Controllers;

import com.project.ticketreservation.Models.Feedback;
import com.project.ticketreservation.Services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @GetMapping("/feedback/count")
    public long countFeedback() {
        return feedbackService.countFeedbacks();
    }
    @GetMapping("/feedbacks")
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/feedbacks/{id}")
    public Feedback getFeedbackById(@PathVariable Integer id) {
        return feedbackService.getFeedbackById(id);
    }
}
