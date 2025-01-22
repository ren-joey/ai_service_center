package com.trod.post.processor;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashSet;
import java.util.Set;

public class DotenvPostProcessor {

    public static void loadEnv() {
        Set<String> shouldExistKeys = new HashSet<>();
        Dotenv keys = Dotenv.configure().filename(".env.example").load();
        keys.entries().forEach(entry -> shouldExistKeys.add(entry.getKey()));

        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> {
            if (!shouldExistKeys.contains(entry.getKey())) {
                throw new RuntimeException("Key " + entry.getKey() + " is not in .env.example");
            } else {
                shouldExistKeys.remove(entry.getKey());
            }
            System.setProperty(entry.getKey(), entry.getValue());
        });

        if (!shouldExistKeys.isEmpty()) {
            throw new RuntimeException("Keys " + shouldExistKeys + " are not in .env");
        }
    }
}
