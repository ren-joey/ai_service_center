package com.asc.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.asc.constant.Text.Env.OPEN_AI_SECRET_KEY;

public class OpenAiSender {
    public static String ASK_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    public static String QUOTA_ENDPOINT = "https://api.openai.com/v1/dashboard/billing/credit_grants";

    public static ResponseEntity<JSONObject> ask(JSONArray messages) {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", "gpt-3.5-turbo");
        jsonBody.put("messages", messages);
        jsonBody.put("temperature", 0.7); // 随机性

        return RestSender.send(
                HttpMethod.POST,
                ASK_ENDPOINT,
                jsonBody,
                getHeaderForOpenAi(),
                JSONObject.class
        );
    }

    public static ResponseEntity<JSONObject> getQuota() {
        return RestSender.send(
                HttpMethod.GET,
                QUOTA_ENDPOINT,
                null,
                getHeaderForOpenAi(),
                JSONObject.class
        );
    }

    private static HttpHeaders getHeaderForOpenAi() {
        String secretKey = System.getProperty(OPEN_AI_SECRET_KEY);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + secretKey);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
