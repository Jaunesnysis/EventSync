package com.example.ibmtask.repository;

import com.example.ibmtask.model.Feedback;
import com.example.ibmtask.model.Sentiment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByEventId(Long eventId);
    //question
    long countByEventIdAndSentiment(Long eventId, Sentiment sentiment);
}
