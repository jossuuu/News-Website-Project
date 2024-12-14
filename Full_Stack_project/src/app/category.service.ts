import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient) { }
  private getCategoryByIdURL = "http://localhost:8080/api/news/newsDistinctCategories";
  private updateCategoryByIdURL = "http://localhost:8080/category/updateCategory";

  getCategoryById(categoryId: any) {
    return this.http.get(`${this.getCategoryByIdURL}/${categoryId}`);
  }

  updateCategoryById(categoryId: any, category: any) {
    return this.http.put(`${this.updateCategoryByIdURL}/${categoryId}`, category);
  }


}
