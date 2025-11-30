package com.example.ibmtask.service;

import com.example.ibmtask.model.Sentiment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class HuggingFaceSentimentAnalysisService implements SentimentAnalysisService {

    private final WebClient webClient;

    public HuggingFaceSentimentAnalysisService(
            @Value("${huggingface.apiUrl}") String apiUrl,
            @Value("${huggingface.apiToken}") String apiToken) {

        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Sentiment analyze(String text) {
        try {
            // Response shape for cardiffnlp/twitter-roberta-base-sentiment:
            // [[{ "label": "LABEL_0", "score": 0.98 }, ...]]
            List<List<Map<String, Object>>> response = webClient.post()
                    .bodyValue(Map.of("inputs", text))
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<List<Map<String, Object>>>>() {})
                    .block();

            if (response == null || response.isEmpty() || response.get(0).isEmpty()) {
                return Sentiment.NEUTRAL;
            }

            List<Map<String, Object>> predictions = response.get(0);

            Map<String, Object> best = predictions.stream()
                    .max(Comparator.comparingDouble(
                            m -> ((Number) m.get("score")).doubleValue()
                    ))
                    .orElse(predictions.get(0));

            String label = (String) best.get("label");
            return mapLabel(label);

        } catch (Exception e) {
            // In case of network / API error
            return Sentiment.NEUTRAL;
        }
    }

    // LABEL_0 = negative, LABEL_1 = neutral, LABEL_2 = positive
    private Sentiment mapLabel(String label) {
        if (label == null) return Sentiment.NEUTRAL;
        String l = label.toUpperCase();

        return switch (l) {
            case "LABEL_2" -> Sentiment.POSITIVE;
            case "LABEL_0" -> Sentiment.NEGATIVE;
            case "LABEL_1" -> Sentiment.NEUTRAL;
            default -> Sentiment.NEUTRAL;
        };
    }
}
