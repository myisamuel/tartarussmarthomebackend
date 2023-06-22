package com.tartarus.model;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
