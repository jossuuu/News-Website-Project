import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {

  constructor(private router: Router) { }

  addNewsbtn() {
    this.router.navigate(['/news'])
  }

  deleteNewsbtn() {
    this.router.navigate(['/deleteNews']);
  }

  updateNewsbtn() {
    this.router.navigate(['/updateNews'])
  }

  newsDetailsbtn() {
    this.router.navigate(['/displayLikesAndDislikes'])
  }

  displayUserbtn() {
    this.router.navigate(['/displayUserDetails']);
  }
  logout() {
    this.router.navigate(['/']);
  }

  newsSortbtn() {
    this.router.navigate(['/sortNews']);
  }

  searchNewsbtn() {
    this.router.navigate(['/searchNewsByAuthor']);
  }
}
