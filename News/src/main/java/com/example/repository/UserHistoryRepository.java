package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.News;
import com.example.model.UserHistory;
import com.example.model.Users;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {
	
    List<UserHistory> findByUser(Users user);
    
    boolean existsByUserAndNews(Users user, News news);   
}

