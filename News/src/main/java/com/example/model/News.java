package com.example.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="News_table")
public class News {
	@Id
	@Column(name="News_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int newsId;
	
	@Column(name="News_Category", length = 200)
	private String newsCategory;
	
	@Column(name="News_title", length = 200)
	private String newsTitle;
	
	@Column(name="News_Description", columnDefinition = "TEXT")
	private String newsDescription;
	
	@Column(name="News_Content", columnDefinition = "TEXT")
	private String newsContent;
	
	@Column(name="News_Image", length = 500)
	private String newsImage;
	
	@Column(name="News_URL", columnDefinition = "TEXT")
	private String newsURL;
	
	@Column(name="Premium_News" , nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'false'")
	private String newsPremium = "false";
	
	@Column(name="News_PublishedAt", length = 20)
	private String newsPublishedAT;
	
	@Column(name="News_author", length = 100)
	private String newsAuthor;
	
	@Column(name="News_Source", length = 50)
	private String newsSource;
	
	@Column(name="News_Country", length = 100)
	private String newsCountry;
	
	@Column(name="News_Likes")
	private Integer newsLikes = 1;
	
	@Column(name="News_DisLikes")
	private Integer newsDislikes = 1;
	
	@OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private List<UserHistory> userHistories;
	

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getNewsCategory() {
		return newsCategory;
	}

	public void setNewsCategory(String newsCategory) {
		this.newsCategory = newsCategory;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsDescription() {
		return newsDescription;
	}

	public void setNewsDescription(String newsDescription) {
		this.newsDescription = newsDescription;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsImage() {
		return newsImage;
	}

	public void setNewsImage(String newsImage) {
		this.newsImage = newsImage;
	}

	public String getNewsPublishedAT() {
		return newsPublishedAT;
	}

	public void setNewsPublishedAT(String newsPublishedAT) {
		this.newsPublishedAT = newsPublishedAT;
	}

	public String getNewsAuthor() {
		return newsAuthor;
	}

	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}

	public String getNewsSource() {
		return newsSource;
	}

	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
	}

	public String getNewsCountry() {
		return newsCountry;
	}

	public void setNewsCountry(String newsCountry) {
		this.newsCountry = newsCountry;
	}

	public String getNewsURL() {
		return newsURL;
	}

	public void setNewsURL(String newsURL) {
		this.newsURL = newsURL;
	}

	public int getNewsLikes() {
		return newsLikes;
	}

	public String getNewsPremium() {
		return newsPremium;
	}

	public void setNewsPremium(String newsPremium) {
		this.newsPremium = newsPremium;
	}

	public void setNewsLikes(Integer newsLikes) {
		this.newsLikes = newsLikes;
	}

	public void setNewsDislikes(Integer newsDislikes) {
		this.newsDislikes = newsDislikes;
	}

	public void setNewsLikes(int newsLikes) {
		this.newsLikes = newsLikes;
	}

	public int getNewsDislikes() {
		return newsDislikes;
	}

	public void setNewsDislikes(int newsDislikes) {
		this.newsDislikes = newsDislikes;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", newsCategory=" + newsCategory + ", newsTitle=" + newsTitle
				+ ", newsDescription=" + newsDescription + ", newsContent=" + newsContent + ", newsImage=" + newsImage
				+ ", newsURL=" + newsURL + ", newsPremium=" + newsPremium + ", newsPublishedAT=" + newsPublishedAT
				+ ", newsAuthor=" + newsAuthor + ", newsSource=" + newsSource + ", newsCountry=" + newsCountry
				+ ", newsLikes=" + newsLikes + ", newsDislikes=" + newsDislikes + "]";
	}

	
}
