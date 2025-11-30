package com.example.ibmtask.service;

import com.example.ibmtask.dto.EventSummaryResponse;
import com.example.ibmtask.dto.FeedbackRequest;
import com.example.ibmtask.dto.FeedbackResponse;
import com.example.ibmtask.model.Event;
import com.example.ibmtask.model.Feedback;
import com.example.ibmtask.model.Sentiment;
import com.example.ibmtask.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final EventService eventService;
    private final SentimentAnalysisService sentimentAnalysisService;

    public FeedbackService(FeedbackRepository feedbackRepository,
                           EventService eventService,
                           SentimentAnalysisService sentimentAnalysisService) {
        this.feedbackRepository = feedbackRepository;
        this.eventService = eventService;
        this.sentimentAnalysisService = sentimentAnalysisService;
    }

    public FeedbackResponse addFeedback(Long eventId, FeedbackRequest request) {
        Event event = eventService.getEventOrThrow(eventId);

        Sentiment sentiment = sentimentAnalysisService.analyze(request.text());

        Feedback feedback = new Feedback();
        feedback.setEvent(event);
        feedback.setText(request.text());
        feedback.setSentiment(sentiment);

        Feedback saved = feedbackRepository.save(feedback);

        return new FeedbackResponse(
                saved.getId(),
                saved.getText(),
                saved.getSentiment().name(),
                saved.getCreatedAt().toString()
        );
    }

    public EventSummaryResponse getSummary(Long eventId) {
        eventService.getEventOrThrow(eventId);

        long positive = feedbackRepository.countByEventIdAndSentiment(eventId, Sentiment.POSITIVE);
        long neutral  = feedbackRepository.countByEventIdAndSentiment(eventId, Sentiment.NEUTRAL);
        long negative = feedbackRepository.countByEventIdAndSentiment(eventId, Sentiment.NEGATIVE);

        long total = positive + neutral + negative;

        return new EventSummaryResponse(eventId, total, positive, neutral, negative);
    }

    public List<FeedbackResponse> listFeedback(Long eventId) {
        return feedbackRepository.findByEventId(eventId)
                .stream()
                .map(f -> new FeedbackResponse(
                        f.getId(),
                        f.getText(),
                        f.getSentiment().name(),
                        f.getCreatedAt().toString()))
                .toList();
    }
}
