package com.blog_app.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service // Marks this class as a Spring service component (business logic layer)
public class AIService {

    @Value("${spring.ai.openai.api-key}") // Injects OpenAI API key from application properties
    private String apiKey;

    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions"; // OpenAI API endpoint

    // Sends a user message to OpenAI and returns the AI-generated response
    public String askOpenAI(String userMessage) {
        RestTemplate restTemplate = new RestTemplate(); // Used to send HTTP requests

        // Set request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Sets content type to JSON
        headers.setBearerAuth(apiKey); // Sets API key for authentication

        // Create request body as a Map (JSON-compatible)
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4o-mini"); // Specifies the AI model to use
        requestBody.put("store", true); // Enables storing responses (if applicable)

        // Add user message to the request
        Map<String, String> userMessageMap = new HashMap<>();
        userMessageMap.put("role", "user"); // Defines user role
        userMessageMap.put("content", "summarize this blog content in minimum words " + userMessage); // Appends user input

        requestBody.put("messages", Collections.singletonList(userMessageMap)); // Wraps message in a list

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers); // Creates HTTP request entity

        try {
            // Sends the POST request to OpenAI API
            ResponseEntity<String> response = restTemplate.exchange(OPENAI_URL, HttpMethod.POST, requestEntity, String.class);

            // Parses JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            // Extracts and returns the AI-generated response
            return jsonNode.path("choices").get(0).path("message").path("content").asText();

        } catch (Exception e) {
            e.printStackTrace(); // Prints error details if API call fails
            return "Error calling OpenAI API: " + e.getMessage(); // Returns error message
        }
    }
}
