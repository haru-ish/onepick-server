package com.example.onepickapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChatGptResponse {

    private List<Choice> choices;

    @Data
    public static class Choice {
        private int index;
        private Message message;
    }
}


