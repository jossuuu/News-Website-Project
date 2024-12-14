package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.News;
import com.example.service.NewsService;

@RestController
@RequestMapping("api/news/")
@CrossOrigin(origins = "http://localhost:4200/")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@PostMapping
	public ResponseEntity<News> addNews(@RequestBody News news){
		return new ResponseEntity<News>(newsService.addNews(news),HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getAllNews")
	public List<News> getAllNews(){
		return newsService.getAllNews();
	}
	
	@GetMapping("{news_id}")//search
	public ResponseEntity<News> getNewsById(@PathVariable int news_id){
		return new ResponseEntity<News>(newsService.getNewsById(news_id), HttpStatus.OK);
	}
	
	@PutMapping("/incrementLikes/{news_id}")
	public ResponseEntity<News>  incrementLike(@PathVariable int news_id){
		return new ResponseEntity<News>(newsService.incrementLike(news_id), HttpStatus.OK);
	}
	
	@PutMapping("/incrementDisLikes/{news_id}")
	public ResponseEntity<News>  incrementDisLike(@PathVariable int news_id){
		return new ResponseEntity<News>(newsService.incrementDisLike(news_id), HttpStatus.OK);
	}
	
	@PutMapping("/updateNewsPremium/{news_id}/{premiumStatus}")
	public ResponseEntity<News> updateNewsPremium(@PathVariable int news_id, @PathVariable String premiumStatus){
		return new ResponseEntity<News>(newsService.updateNewsPremiumById(news_id,  premiumStatus), HttpStatus.OK);
	}
	
	@GetMapping("/paginated/{page}/{size}")
	public ResponseEntity<Page<News>> getPaginatedNews(@PathVariable int page, @PathVariable int size){
		return new ResponseEntity<>(newsService.getNews(page, size), HttpStatus.OK);
	}
	
	@GetMapping("/filter/{field}/{page}/{size}")
	public ResponseEntity<Page<News>> filterNews(@PathVariable String field, @PathVariable int page, @PathVariable int size){
		Page<News> allNews = newsService.sortNews(field, page, size);
		return new ResponseEntity<>(allNews, HttpStatus.OK);
}
	
	@DeleteMapping("/deleteNews/{news_id}")
	public List<News> deleteNews(@PathVariable int news_id){
		return (newsService.deleteNewsById(news_id));
	}
	
	@PutMapping("/updateNews/{news_id}")
	public List<News> updateNews(@PathVariable int news_id, @RequestBody News news){
		return (newsService.updateNewsById(news_id, news));
	}
	
	@GetMapping("/newsByTitle/{newsTitle}")
	public List<News> getNewsByTitle(@PathVariable String newsTitle){
		return (newsService.getNewsByTitle(newsTitle));
	}
	
	@GetMapping("/newsByCategoryWithPagination/{newsCategory}/{page}/{size}")
	public ResponseEntity<Page<News>> getNewsByCategory(@PathVariable String newsCategory, @PathVariable int page, @PathVariable int size){
		return new ResponseEntity<>(newsService.getNewsByCategoryWithPagination(newsCategory, page, size), HttpStatus.OK);
	}
	
	@GetMapping("/newsDistinctCategories")
	public List<String> getNewsCategories(){
		return (newsService.getNewsCategories());
	}
	
	@GetMapping("/searchByAuthor/{newsAuthor}")
	public List<News> searchByNewsAuthor(@PathVariable String newsAuthor){
		return (newsService.searchNewsByAuthor(newsAuthor));
	}

}

