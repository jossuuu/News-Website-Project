package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.UserHistory;
import com.example.service.UserHistoryService;

@RestController
@RequestMapping("userHistory")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserHistoryController {
	
	@Autowired
	private UserHistoryService userHistoryService;
	
	
	@PostMapping("/history/{userId}/{newsId}")
	public ResponseEntity<String> addToUserHistory(@PathVariable int userId, @PathVariable int newsId) {
	    userHistoryService.addToUserHistory(userId, newsId);
	    return ResponseEntity.ok("History updated successfully");
	}
	
	@GetMapping("/history/{userId}")
	public ResponseEntity<List<UserHistory>> getUserHistory(@PathVariable int userId) {
	    List<UserHistory> history = userHistoryService.getUserHistory(userId);
	    return ResponseEntity.ok(history);
	}
}
