import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private getAllUsersURL = "http://localhost:8080/users/getAllUsers";
  private getUserByIdURL = "http://localhost:8080/users/GetById";
  private changeSubStatusURL = "http://localhost:8080/users/updateUserSubscriptionStatus";

  constructor(private http: HttpClient) { }

  getAllUsers() {
    return this.http.get(`${this.getAllUsersURL}`);
  }

  getUserById(userId: any) {
    return this.http.get(`${this.getUserByIdURL}/${userId}`);
  }

  changeUserSubStatus(userId: any, status: any) {
    return this.http.put(`${this.changeSubStatusURL}/${userId}/${status}`, {});
  }
}
