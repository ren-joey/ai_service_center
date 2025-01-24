package com.asc.service;

import com.asc.constant.OpenAiTextModelEnum;
import com.asc.util.OpenAiSender;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class OpenAiService {
    public JSONObject getQuota() {
        return OpenAiSender.getQuota().getBody();
    }

    public JSONObject ask(OpenAiTextModelEnum model, String[] messages) {
        JSONArray messagePackage = new JSONArray();
        messagePackage.put(new JSONObject().put("role", "system").put("content", "You are a helpful assistant."));
        for(String message : messages) {
            messagePackage.put(new JSONObject().put("role", "user").put("content", message));
        }

        return OpenAiSender.ask(model, messagePackage).getBody();
    }
}
