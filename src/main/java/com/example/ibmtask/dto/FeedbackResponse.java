package com.example.ibmtask.dto;

public record FeedbackResponse(Long id, String text, String sentiment, String createdAt) {}
