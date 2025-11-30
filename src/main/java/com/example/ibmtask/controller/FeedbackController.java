package com.example.ibmtask.controller;

import com.example.ibmtask.dto.EventSummaryResponse;
import com.example.ibmtask.dto.FeedbackRequest;
import com.example.ibmtask.dto.FeedbackResponse;
import com.example.ibmtask.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events/{eventId}")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/feedback")
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackResponse addFeedback(@PathVariable Long eventId,
                                        @RequestBody FeedbackRequest request) {
        return feedbackService.addFeedback(eventId, request);
    }

    @GetMapping("/feedback")
    public List<FeedbackResponse> listFeedback(@PathVariable Long eventId) {
        return feedbackService.listFeedback(eventId);
    }

    @GetMapping("/summary")
    public EventSummaryResponse getSummary(@PathVariable Long eventId) {
        return feedbackService.getSummary(eventId);
    }
}
