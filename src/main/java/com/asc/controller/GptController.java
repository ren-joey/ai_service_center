package com.asc.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.asc.constant.Text.Env.OPEN_AI_SECRET_KEY;

@RestController
@RequestMapping("/gpt")
public class GptController {
    @GetMapping("/quota")
    public JSONObject getQuota() {
        String secretKey = System.getProperty(OPEN_AI_SECRET_KEY);
        String endPoint = "https://api.openai.com/v1/dashboard/billing/credit_grants";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretKey);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ResponseEntity<JSONObject> response = restTemplate.exchange(
                URI.create(endPoint),
                HttpMethod.GET,
                request,
                JSONObject.class
        );
        return response.getBody();
    }

    @PostMapping("/ask")
    public JSONObject ask() {
        String secretKey = System.getProperty(OPEN_AI_SECRET_KEY);
        String endPoint = "https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretKey);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        JSONObject jsonBody = new JSONObject();
        JSONArray messages = new JSONArray();
        try {
            jsonBody.put("model", "gpt-3.5-turbo");
            messages.put(new JSONObject().put("role", "system").put("content", "You are a helpful assistant."));
            messages.put(new JSONObject().put("role", "user").put("content", "How do I use ChatGPT-4 API in Java?"));
            jsonBody.put("messages", messages);
            jsonBody.put("temperature", 0.7); // 随机性
        } catch (JSONException e) {
            throw new RuntimeException("Failed to create JSON body for OpenAI request.");
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(jsonBody.toString(), headers);
        ResponseEntity<JSONObject> response = restTemplate.exchange(
                URI.create(endPoint),
                HttpMethod.POST,
                request,
                JSONObject.class
        );
        return response.getBody();
    }
}
