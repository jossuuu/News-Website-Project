package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.model.News;

public interface NewsService {

	public News addNews(News news); //create or add article

	public List<News> getAllNews(); // get all articles

	public News getNewsById(int news_id); //get article by id

	public Page<News> getNewsByCategoryWithPagination(String newsCategory, int page, int size); //get article by category

	public  List<News>getNewsByTitle(String keyword); //get article by keyword

	public List<News> deleteNewsById(int news_id);  //delete an article by ID

	public List<News> updateNewsById(int news_id, News updatedNews); //update an article by ID

	public Page<News> getNews(int page, int size);

	public Page<News> sortNews(String field, int page, int size);

	public News incrementLike(int news_id);

	public News incrementDisLike(int news_id);
	
	public List<String> getNewsCategories();
	
	public List<News> searchNewsByAuthor(String newsAuthor);
	
	public News updateNewsPremiumById(int news_id, String newsPremium);

}
