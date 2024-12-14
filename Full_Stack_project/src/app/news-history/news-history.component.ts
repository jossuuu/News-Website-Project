import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-news-history',
  templateUrl: './news-history.component.html',
  styleUrl: './news-history.component.css'
})
export class NewsHistoryComponent implements OnInit {

  userHistories: any;
  userName: any;
  userRole: any;
  userId: any;
  currUserId: any;
  currUserInterest: any;

  constructor(private newsService: NewsService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.currUserId = this.activatedRoute.snapshot.params['userId'];
    this.currUserInterest = this.activatedRoute.snapshot.params['userInterest'];
    this.getAllNewsHistory(this.currUserId);
  }

  getAllNewsHistory(userId: any) {
    return this.newsService.getAllNewsHistory(userId).subscribe((response: any) => {
      this.userHistories = response;
      // this.userName = response.user.usersName;
      console.log(response);
      // this.userRole = response.user.userRole;
    });
  }
  goBack() {
    this.router.navigate(['homepage', this.currUserId, this.currUserInterest]);
  }
}
