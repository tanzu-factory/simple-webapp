package com.example.servingwebcontent;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public class AppProperties {
    
    private String greetingText;

    public String getGreetingText() {
        return this.greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }    
}
