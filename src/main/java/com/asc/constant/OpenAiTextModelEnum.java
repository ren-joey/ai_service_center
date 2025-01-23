package com.asc.constant;

import lombok.Getter;

@Getter
public enum OpenAiTextModelEnum {
    // Text tokens
    gpt_4o("gpt-4o"),
    gpt_4o_mini("gpt-4o-mini"),
    o1("o1"),
    o1_mini("o1-mini"),

    // Other models
    chatgpt_4o_latest("chatgpt-4o-latest"),
    gpt_4_turbo("gpt-4-turbo"),
    gpt_4("gpt-4"),
    gpt_4_32k("gpt-4-32k"),
    gpt_35_turbo("gpt-3.5-turbo"),
    gpt_35_turbo_instruct("gpt-3.5-turbo-instruct"),
    gpt_35_turbo_16k_0613("gpt-3.5-turbo-16k-0613"),
    davinci_002("davinci-002"),
    babbage_002("babbage-002");

    private final String model;

    OpenAiTextModelEnum(String model) {
        this.model = model;
    }

    public static OpenAiTextModelEnum fromString(String model) {
        for (OpenAiTextModelEnum modelEnum : OpenAiTextModelEnum.values()) {
            if (modelEnum.model.equals(model)) {
                return modelEnum;
            }
        }
        return null;
    }
}
