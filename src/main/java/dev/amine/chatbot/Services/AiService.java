package dev.amine.chatbot.Services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class AiService {

    private final String apiKey = System.getProperty("GEMINI_API_KEY");
    private final String apiUrl = System.getProperty("GEMINI_API_URL");
    private final RestTemplate restTemplate;

    public AiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateContent(String prompt) {
        if (prompt == null || prompt.isEmpty()) {
            throw new IllegalArgumentException("Prompt cannot be null or empty");
        }

        // Correct payload structure
        String requestBody = """
                {
                    "contents": [
                        {
                            "parts": [
                                { "text": "%s" }
                            ]
                        }
                    ]
                }
                """.formatted(prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl + "?key=" + apiKey, HttpMethod.POST, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // Log the error for debugging
            System.err.println("API Error: " + e.getResponseBodyAsString());
            throw new RuntimeException("Error communicating with the API", e);
        }
    }


}