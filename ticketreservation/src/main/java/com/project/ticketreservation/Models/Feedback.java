package com.project.ticketreservation.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "Feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Column(length = 200)
    private String content;

    @Column(name = "feedback_date")
    private LocalDate feedbackDate;
    @Min(0)
    @Max(5)
    private Integer rate;

    @Column(name = "passenger_id", length = 25)
    private String passengerId;
    @ManyToOne
    @JoinColumn(name = "passenger_id", insertable = false, updatable = false)
    private Passenger passenger;

    public Feedback() {
    }

    public Feedback(Integer feedbackId, String content, LocalDate feedbackDate, @Min(0) @Max(5) Integer rate,
            String passengerId, Passenger passenger) {
        this.feedbackId = feedbackId;
        this.content = content;
        this.feedbackDate = feedbackDate;
        this.rate = rate;
        this.passengerId = passengerId;
        this.passenger = passenger;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(LocalDate feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
