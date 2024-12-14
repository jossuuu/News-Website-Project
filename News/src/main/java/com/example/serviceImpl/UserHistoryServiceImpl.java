package com.example.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.News;
import com.example.model.UserHistory;
import com.example.model.Users;
import com.example.repository.NewsRepository;
import com.example.repository.UserHistoryRepository;
import com.example.repository.UserRepository;
import com.example.service.UserHistoryService;

@Service
public class UserHistoryServiceImpl  implements UserHistoryService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private UserHistoryRepository userHistoryRepository;
	
	public void addToUserHistory(int userId, int newsId) {
	    Users user = userRepository.findById(userId)
	                 .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
	    News news = newsRepository.findById(newsId)
	                 .orElseThrow(() -> new ResourceNotFoundException("News", "newsId", newsId));

	    if (!userHistoryRepository.existsByUserAndNews(user, news)) {
	        // Save new history record
	        UserHistory history = new UserHistory();
	        history.setUser(user);
	        history.setNews(news);
	        history.setViewedAt(LocalDateTime.now());
	        userHistoryRepository.save(history);
	    }
	}

	@Override
	public List<UserHistory> getUserHistory(int userId) {
	    Users user = userRepository.findById(userId)
	                 .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
	    return userHistoryRepository.findByUser(user); // You can create a custom query to fetch all histories for a user
	}

}
