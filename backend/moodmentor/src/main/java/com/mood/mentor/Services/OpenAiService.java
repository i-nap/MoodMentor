package com.mood.mentor.Services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    private final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent";
    private final String API_KEY = System.getenv("GOOGLE_API_KEY");

    public String getCustomizedResponse(List<Map<String, String>> chatHistory) {
        RestTemplate restTemplate = new RestTemplate();

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key", API_KEY);



        // Convert chat history to Gemini's format
        List<Map<String, Object>> contents = new ArrayList<>();
        for (Map<String, String> message : chatHistory) {
            Map<String, Object> content = new HashMap<>();
            // Map OpenAI roles to Gemini roles
            String originalRole = message.get("role");
            String geminiRole = mapToGeminiRole(originalRole);

            content.put("role", geminiRole);
            content.put("parts", List.of(Map.of("text", message.get("content"))));
            contents.add(content);
        }

        // Create the request body
        Map<String, Object> body = new HashMap<>();
        body.put("contents", contents);
        body.put("generationConfig", Map.of(
                "temperature", 0.7,
                "maxOutputTokens", 300
        ));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            // Make the POST request to the Gemini API
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    GEMINI_API_URL,
                    request,
                    Map.class
            );

            // Parse the response
            Map responseBody = response.getBody();
            if (responseBody != null) {
                List<Map> candidates = (List<Map>) responseBody.get("candidates");
                if (candidates != null && !candidates.isEmpty()) {
                    Map content = (Map) candidates.get(0).get("content");
                    if (content != null) {
                        List<Map> parts = (List<Map>) content.get("parts");
                        if (parts != null && !parts.isEmpty()) {
                            return (String) parts.get(0).get("text");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }

        return "Error: Could not get a response.";
    }

    private String mapToGeminiRole(String openAiRole) {
        return switch (openAiRole.toLowerCase()) {
            case "assistant" -> "model";
            case "system" -> "user"; // Handle system messages as user messages
            case "user" -> "user";
            default -> "user"; // Default to user role for unknown roles
        };
    }
}