package com.example.serviceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.exception.ResourceNotFoundException;
import com.example.model.News;
import com.example.repository.NewsRepository;
import com.example.service.NewsService;
@Service
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsRepository newsRepository;
	

	@Override
	public News addNews(News news) {  //adds article 
		return newsRepository.save(news);
	}

	@Override
	public List<News> getAllNews() {
		return newsRepository.findAll();//returning all records
		
	}

	@Override
	public News getNewsById(int news_id) {
		return newsRepository.findById(news_id).orElseThrow(() -> new ResourceNotFoundException("News", "news_id", news_id));
	}
	
	@Override
	public Page<News> getNewsByCategoryWithPagination(String newsCategory, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return newsRepository.findByNewsCategory(newsCategory, pageable);
	}
	
	
	@Override
	public Page<News> getNews(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return newsRepository.findAll(pageable);
	}

	@Override
	public List<News> getNewsByTitle(String keyword) {
		return newsRepository.findByNewsTitle(keyword);
	}

	@Override
	public List<News> deleteNewsById(int news_id) {
		News news = getNewsById(news_id);
		newsRepository.deleteById(news.getNewsId());
		return newsRepository.findAll();
	}
	

	@Override
	public List<News> updateNewsById(int news_id, News updatedNews) {
	    News news1 = getNewsById(news_id);
	    news1.setNewsCategory(updatedNews.getNewsCategory());
	    news1.setNewsTitle(updatedNews.getNewsTitle());
	    news1.setNewsPremium(updatedNews.getNewsPremium());
	    news1.setNewsDescription(updatedNews.getNewsDescription());
	    news1.setNewsContent(updatedNews.getNewsContent());
	    news1.setNewsImage(updatedNews.getNewsImage());
	    news1.setNewsURL(updatedNews.getNewsURL());
	    news1.setNewsPublishedAT(updatedNews.getNewsPublishedAT());
	    news1.setNewsAuthor(updatedNews.getNewsAuthor());
	    news1.setNewsSource(updatedNews.getNewsSource());
	    news1.setNewsCountry(updatedNews.getNewsCountry());
	    newsRepository.save(news1); 
	    return newsRepository.findAll();
	}
	
	@Override
	public Page<News> sortNews(String field, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, field));
	    return newsRepository.findAll(pageable);
	}


	@Override
	public News incrementLike(int news_id) {
		 News news = getNewsById(news_id);
		 news.setNewsLikes(news.getNewsLikes()+1);
		 return newsRepository.save(news);
	}

	@Override
	public News incrementDisLike(int news_id) {
		News news = getNewsById(news_id);
		 news.setNewsDislikes(news.getNewsDislikes()+1);
		 return newsRepository.save(news);
	}

	@Override
	public List<String> getNewsCategories() {
		return newsRepository.findDistinctCategories();
	}

	@Override
	public List<News> searchNewsByAuthor(String newsAuthor) {
		return newsRepository.findByNewsAuthor(newsAuthor);
	}

	@Override
	public News updateNewsPremiumById(int newsId, String newsPremium) {
		 News news = getNewsById(newsId);
		    news.setNewsPremium(newsPremium); // Update only the newsPremium field
		    newsRepository.save(news);
		    return news;
	}

}
