package com.example.servingwebcontent.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@ConfigurationProperties("app")
public class GreetingResponseObject {
    
    @JsonProperty("greeting_text")
    private String greetingText;

    public GreetingResponseObject() {}

    public GreetingResponseObject(String greetingText) {
        this.greetingText = greetingText;
    }

    public String getGreetingText() {
        return this.greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }

    @Override
    public String toString() {
        return "{" +
            " greetingText='" + greetingText + "'" +
            "}";
    }



}
