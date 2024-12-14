import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-search-by-author',
  templateUrl: './search-by-author.component.html',
  styleUrl: './search-by-author.component.css'
})
export class SearchByAuthorComponent implements OnInit {

  newsList: any;
  searchQuery: any;
  searchClicked: boolean = false;
  currUserId: any;
  currUserInterest: any;
  currSubscriptionStatus: any;

  constructor(private newsService: NewsService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.currUserId = this.activatedRoute.snapshot.params['userId'];
    this.currUserInterest = this.activatedRoute.snapshot.params['userInterest'];
    this.currSubscriptionStatus = this.activatedRoute.snapshot.params['subscriptionStatus'];
  }

  searchNews(newsAuthor: any) {
    this.searchClicked = true;
    if (this.searchQuery.trim() === '') {
      this.newsList = [];
      return;
    }
    this.searchNewsByAuthor(newsAuthor);
  }

  searchNewsByAuthor(newsAuthor: any) {
    this.newsService.searchNewsByAuthor(newsAuthor).subscribe((response: any) => {
      this.newsList = response;
    })
  }

  goBack() {
    this.router.navigate(['homepage', this.currUserId, this.currUserInterest, this.currSubscriptionStatus]);
  }

}
