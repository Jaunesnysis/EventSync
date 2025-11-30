package com.example.ibmtask.service;

import com.example.ibmtask.model.Sentiment;

public interface SentimentAnalysisService {
    Sentiment analyze(String text);
}
