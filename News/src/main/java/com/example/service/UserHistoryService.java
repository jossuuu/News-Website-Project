package com.example.service;

import java.util.List;

import com.example.model.UserHistory;

public interface UserHistoryService {
	
	public void addToUserHistory(int userId, int newsId);
	
	public List<UserHistory> getUserHistory(int userId);	

}
