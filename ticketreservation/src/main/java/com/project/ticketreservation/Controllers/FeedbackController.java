package com.project.ticketreservation.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.ticketreservation.Models.Feedback;
import com.project.ticketreservation.Services.FeedbackService;

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
    @PostMapping("/create/feedback")
    public Feedback createFeedback(@RequestBody Map<String, Object> feedbackMap) {
        String content = (String) feedbackMap.get("content");
        Integer rate = (Integer) feedbackMap.get("rate");
        String passengerId = (String) feedbackMap.get("passenger_id");
        Feedback feedback = new Feedback(content,rate,passengerId);
        return(feedbackService.addFeedback(feedback));
    }


//    @PostMapping("/create/feedback")
//    public Feedback createFeedback(@RequestBody Feedback feedback) {
//        return(feedbackService.addFeedback(feedback));
//    }

}
