package com.example.ibmtask.dto;

public record EventSummaryResponse(
        Long eventId,
        long totalFeedbacks,
        long positiveCount,
        long neutralCount,
        long negativeCount
) {}
