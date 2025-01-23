package com.asc.controller;

import com.asc.util.OpenAiSender;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gpt")
public class GptController {
    @GetMapping("/quota")
    public JSONObject getQuota() {
        return OpenAiSender.getQuota().getBody();
    }

    @PostMapping("/ask")
    public JSONObject ask() {
        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "system").put("content", "You are a helpful assistant."));
        messages.put(new JSONObject().put("role", "user").put("content", "How do I use ChatGPT-4 API in Java?"));
        return OpenAiSender.ask(messages).getBody();
    }
}
