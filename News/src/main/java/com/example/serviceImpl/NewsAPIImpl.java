package com.example.serviceImpl;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.model.News;
import com.example.model.NewsApiResponse;
import com.example.model.NewsArticle;
import com.example.repository.NewsRepository;
import com.example.service.NewsAPI;

@Service
public class NewsAPIImpl implements NewsAPI {

    @Value("${newsapi.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final NewsRepository newsRepository;

    public NewsAPIImpl(RestTemplate restTemplate, NewsRepository newsRepository) {
        this.restTemplate = restTemplate;
        this.newsRepository = newsRepository;
    }

    private News mapToNewsEntity(NewsArticle newsArticle, String category, String country) {
        News news = new News();
        news.setNewsTitle(newsArticle.getTitle());
        news.setNewsContent(newsArticle.getContent());
        news.setNewsImage(newsArticle.getUrlToImage());
        news.setNewsAuthor(newsArticle.getAuthor());
        news.setNewsDescription(newsArticle.getDescription());
        news.setNewsURL(newsArticle.getUrl());
        news.setNewsSource("NewsAPI"); // Example source
        news.setNewsCountry(country);     // Modify as needed
        news.setNewsCategory(category);
        news.setNewsLikes(1);
        news.setNewsDislikes(1);

        if (newsArticle.getPublishedAt() != null && !newsArticle.getPublishedAt().isEmpty()) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_DATE_TIME; // Input format: '2024-11-23T05:07:00Z'
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(newsArticle.getPublishedAt(), inputFormatter);
            
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Desired format: dd-MM-yyyy
            String formattedDate = zonedDateTime.format(outputFormatter);
            news.setNewsPublishedAT(formattedDate); // Save formatted date as String
        }



        // Validate that all required fields are non-null
        if (isAnyFieldNull(news)) {
            return null; // Return null to indicate invalid data
        }
        return news;
    }

    private boolean isAnyFieldNull(News news) {
        return (news.getNewsTitle() == null || news.getNewsContent() == null || news.getNewsImage() == null ||
               news.getNewsAuthor() == null || news.getNewsDescription() == null || news.getNewsPublishedAT() == null || news.getNewsURL() == null);
    }
    
    
    
    
    
    
    
    
    
    
    @Override
    public NewsApiResponse getNews(String category, String country) {
        String url = "https://newsapi.org/v2/top-headlines?category=" + category +
                     "&country=" + country + "&apiKey=" + apiKey;
        NewsApiResponse response = restTemplate.getForObject(url, NewsApiResponse.class);
        
        if (response != null && response.getArticles() != null) {
            response.getArticles().forEach(article -> {
                // Map the article to a News entityz
                News news = mapToNewsEntity(article, category, country);
                
                if (news == null) {
                    System.out.println("Skipping news article due to missing required fields: ");
                    return; // Skip further processing for this article
                }
                
                // Check for duplicates and save valid news objects
                if (!newsRepository.existsByNewsTitle(news.getNewsTitle())) {
                    newsRepository.save(news);
                }
            });
        }
        return response;
    }
}
    







