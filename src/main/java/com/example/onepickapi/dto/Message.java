package com.example.onepickapi.dto;

import lombok.Data;

@Data
public class Message {

    private String role;
    private String content;

    Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    Message() {
    }

}
