package com.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.NewsApiResponse;
import com.example.service.NewsAPI;

@RestController
@RequestMapping("news")
@CrossOrigin(origins = "http://localhost:4200/")
public class NewsApiController {

    private final NewsAPI newsApi; // Autowired by Spring

    public NewsApiController(NewsAPI newsApi) {
        this.newsApi = newsApi;
    }

    @GetMapping
    public NewsApiResponse fetchNews(@RequestParam String category, @RequestParam String country) {
        return newsApi.getNews(category, country);
    }
}