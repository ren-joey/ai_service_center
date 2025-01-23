package com.asc.util;

import jakarta.annotation.Nullable;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

public class RestSender {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static <T> ResponseEntity<T> send (
            HttpMethod method,
            String url,
            @Nullable JSONObject request,
            HttpHeaders headers,
            Class<T> responseType
    ) {
        String body = request == null ? null : request.toString();
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(
                URI.create(url),
                method,
                entity,
                responseType
        );
    }
}
