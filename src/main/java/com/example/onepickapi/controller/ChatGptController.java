package com.example.onepickapi.controller;

import com.example.onepickapi.dto.ChatGptRequest;
import com.example.onepickapi.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatGptController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @GetMapping("/api/movie-title")
    public String getKeyWords(@RequestParam String keyword1, @RequestParam String keyword2, @RequestParam String keyword3) {
        String prompt = keyword1 + "、" + keyword2 + "、"+ keyword3 + "の3つのワードに当てはまる映画を一つだけ教えて下さい。ランダムにお願いします。" +
                "回答は以下の形で返して下さい。それ以外の情報は何も返さないで下さい。「」";
        return getMovieTitle(prompt);
    }

    public String getMovieTitle(@RequestParam String prompt) {
        ChatGptRequest request = new ChatGptRequest(model, prompt);

        ChatGptResponse response = restTemplate.postForObject(
                apiUrl,
                request,
                ChatGptResponse.class);

        if(response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "";
        }
        return response.getChoices().get(0).getMessage().getContent();
    }
}
