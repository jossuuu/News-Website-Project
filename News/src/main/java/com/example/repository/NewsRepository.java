package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer>{ // JpaRepository takes 2 parameters, one is Class which has @Entity and another Interger specifies the primary key datatype in that @entity class
	
	@Query("SELECT DISTINCT n.newsCategory FROM News n")
	List<String> findDistinctCategories();
	
	boolean existsByNewsTitle(String newsTitle);
	
	public List<News> findByNewsTitle(String keyword);
	
	public Page<News> findByNewsCategory(String newsCategory, Pageable pageable);
	
	public List<News> findByNewsAuthor(String newsAuthor);
}
