package com.asc.controller;

import com.asc.constant.OpenAiTextModelEnum;
import com.asc.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class OpenAiController {
    private final OpenAiService openAiService;

    @GetMapping("/quota")
    public JSONObject getQuota() {
        return openAiService.getQuota();
    }

    @PostMapping("/ask")
    public JSONObject ask(
        @RequestParam() OpenAiTextModelEnum model,
        @RequestParam() String[] messages
    ) {
        return openAiService.ask(model, messages);
    }
}
