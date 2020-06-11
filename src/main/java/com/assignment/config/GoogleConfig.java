package com.assignment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleConfig {
    @Value("${google.urlPattern}")
    private String urlPattern;

    @Value("${google.resultCount:100}")
    private int resultCount;

    public String getUrlPattern() {
        return urlPattern;
    }

    public int getResultCount() {
        return resultCount;
    }
}