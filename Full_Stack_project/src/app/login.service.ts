import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})

export class AuthService {
  private loginUrl = 'http://localhost:8080/users/login';
  private signupUrl = 'http://localhost:8080/users';
  

  constructor(private http: HttpClient) { }
  login(user2: any) {
    return this.http.post(`${this.loginUrl}`, user2);
  }

  signup(user1: any) {
    console.log("-----service-----")
    console.log(user1);
    return this.http.post(`${this.signupUrl}`, user1);
  }
}