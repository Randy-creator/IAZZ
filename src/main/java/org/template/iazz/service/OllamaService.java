package org.template.iazz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OllamaService {

  private final RestTemplate restTemplate = new RestTemplate();
  private final ObjectMapper objectMapper = new ObjectMapper();

  public String askDeepseekR1(String prompt) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, Object> body = new HashMap<>();
    body.put("model", "deepseek-r1");
    body.put("prompt", prompt);
    body.put("stream", false);

    HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

    ResponseEntity<String> response =
        restTemplate.exchange(
            "http://localhost:11434/api/generate", HttpMethod.POST, request, String.class);

    try {
      Map<String, Object> json = objectMapper.readValue(response.getBody(), Map.class);
      return (String) json.get("response");
    } catch (Exception e) {
      e.printStackTrace();
      return "Erreur lors de l'analyse de la réponse Ollama.";
    }
  }
}
