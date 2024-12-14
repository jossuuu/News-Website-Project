import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  private getAllNewsURL = "http://localhost:8080/api/news/getAllNews";
  private paginatedURL = "http://localhost:8080/api/news/paginated"
  private incrementLikesURL = "http://localhost:8080/api/news/incrementLikes"
  private incrementDisLikesURL = "http://localhost:8080/api/news/incrementDisLikes"
  private addHistoryURL = "http://localhost:8080/userHistory/history"
  private getAllHistoryURL = "http://localhost:8080/userHistory/history"
  private getNewsByCategoryWithPaginationURL = "http://localhost:8080/api/news/newsByCategoryWithPagination"
  private sortByNewsURL = "http://localhost:8080/api/news/filter"
  private getCategoryFromNewsURL = "http://localhost:8080/api/news/newsDistinctCategories";
  private searchNewsByAuthorURL = "http://localhost:8080/api/news/searchByAuthor"

  constructor(private http: HttpClient) { }

  getAllNews() {
    return this.http.get(`${this.getAllNewsURL}`);
  }

  sortByNews(field: any, pageIndex: any, pageSize: any) {
    return this.http.get(`${this.sortByNewsURL}/${field}/${pageIndex}/${pageSize}`);
  }

  searchNewsByAuthor(newsAuthor: any) {
    return this.http.get(`${this.searchNewsByAuthorURL}/${newsAuthor}`);
  }

  getCategoriesFromNews() {
    return this.http.get(`${this.getCategoryFromNewsURL}`);
  }

  getNewsByCategoryWithPagination(newsCategory: any, pageIndex: any, pageSize: any) {
    return this.http.get(`${this.getNewsByCategoryWithPaginationURL}/${newsCategory}/${pageIndex}/${pageSize}`);
  }

  getPaginatedNews(pageIndex: any, pageSize: any) {
    return this.http.get(`${this.paginatedURL}/${pageIndex}/${pageSize}`);
  }

  incrementLike(newsId: any) {
    return this.http.put(`${this.incrementLikesURL}/${newsId}`, {});
  }

  incrementDisLike(newsId: any) {
    return this.http.put(`${this.incrementDisLikesURL}/${newsId}`, {});
  }

  updateHistory(userId: any, newsId: any) {
    return this.http.post(`${this.addHistoryURL}/${userId}/${newsId}`, {});
  }

  getAllNewsHistory(userId: any) {
    return this.http.get(`${this.getAllHistoryURL}/${userId}`);
  }
}
