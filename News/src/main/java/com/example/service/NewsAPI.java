package com.example.service;


import com.example.model.NewsApiResponse;

public interface NewsAPI {
	public NewsApiResponse getNews(String category, String country);
}

