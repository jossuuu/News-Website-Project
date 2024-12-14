package com.example.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="User_History_table")
public class UserHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="User_History_ID")
	private int userHistoryId;

	@ManyToOne
	@JoinColumn(name="User_ID")
	private Users user;

	@ManyToOne
	@JoinColumn(name="News_ID")
	private News news;

	@Column(name="Viewed_At", nullable = false)
	private LocalDateTime viewedAt;

	public int getUserHistoryId() {
		return userHistoryId;
	}

	public void setUserHistoryId(int userHistoryId) {
		this.userHistoryId = userHistoryId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public LocalDateTime getViewedAt() {
		return viewedAt;
	}

	public void setViewedAt(LocalDateTime viewedAt) {
		this.viewedAt = viewedAt;
	}
	
}
