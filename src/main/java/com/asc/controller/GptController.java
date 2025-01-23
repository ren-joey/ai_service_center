package com.asc.controller;

import com.asc.constant.OpenAiTextModelEnum;
import com.asc.util.OpenAiSender;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gpt")
public class GptController {
    @GetMapping("/quota")
    public JSONObject getQuota() {
        return OpenAiSender.getQuota().getBody();
    }

    @PostMapping("/ask")
    public JSONObject ask(
        @RequestParam() OpenAiTextModelEnum model,
        @RequestParam() String[] messages
    ) {
        JSONArray messagePackage = new JSONArray();
        messagePackage.put(new JSONObject().put("role", "system").put("content", "You are a helpful assistant."));
        for(String message : messages) {
            messagePackage.put(new JSONObject().put("role", "user").put("content", message));
        }

        return OpenAiSender.ask(model, messagePackage).getBody();
    }
}
