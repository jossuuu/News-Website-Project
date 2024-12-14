import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private addCategoryURL = "http://localhost:8080/category";
  private getCategoryByIdURL = "http://localhost:8080/category/getCategoryById";
  private updateCategoryURL = "http://localhost:8080/category/updateCategory";
  private addNewsURL = "http://localhost:8080/api/news/";
  private deleteNewsURL = "http://localhost:8080/api/news/deleteNews";
  private updateNewsURL = "http://localhost:8080/api/news/updateNews";
  private getNewsByIdURL = "http://localhost:8080/api/news";
  private getCategoryFromNewsURL = "http://localhost:8080/api/news/newsDistinctCategories";
  private setPremiumNewsURL = "http://localhost:8080/api/news/updateNewsPremium"

  constructor(private http: HttpClient) { }


  getCategoriesFromNews() {
    return this.http.get(`${this.getCategoryFromNewsURL}`);
  }

  addNews(news: any) {
    return this.http.post(`${this.addNewsURL}`, news)
  }

  deleteNewsById(newsId: any) {
    return this.http.delete(`${this.deleteNewsURL}/${newsId}`);
  }

  updateNewsById(newsId: any, news: any) {
    return this.http.put(`${this.updateNewsURL}/${newsId}`, news);
  }

  getNewsById(newsId: any) {
    return this.http.get(`${this.getNewsByIdURL}/${newsId}`);
  }

  getCategoryById(categoryId: any) {
    return this.http.get(`${this.getCategoryByIdURL}/${categoryId}`);
  }

  setNewsPremium(newsId: any, status: any) {
    return this.http.put(`${this.setPremiumNewsURL}/${newsId}/${status}`, {});
  }
}
