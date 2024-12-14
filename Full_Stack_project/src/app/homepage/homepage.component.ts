import { Component, OnInit } from '@angular/core';
import { NewsService } from '../news.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css',
})
export class HomepageComponent implements OnInit {
  newsList: any;
  pageRange: number[] = [];
  totalPages: any = 0;
  totalElements: any = 0;
  pageIndex: any = 0;
  pageSize: any = 4;
  item: any;
  currUserId: any;
  currUserInterest: any;
  currUserSubscriptionStatus: any;
  categories: string[] = [];
  currentView: 'all' | 'category' = 'category';


  constructor(
    private newsService: NewsService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.currUserId = this.activatedRoute.snapshot.params['userId'];
    this.currUserInterest = this.activatedRoute.snapshot.params['userInterest'];
    this.currUserSubscriptionStatus = this.activatedRoute.snapshot.params['subscriptionStatus']
    console.log(this.currUserSubscriptionStatus);
    this.fetchCategories();
    this.fetchNewsByCategory();
    this.updatePageRange();
  }

  fetchNews() {
    this.currentView = 'all';
    this.pageIndex = 0;
    this.newsService
      .getPaginatedNews(this.pageIndex, this.pageSize)
      .subscribe((response: any) => {
        this.newsList = response.content;
        this.totalPages = response.totalPages;
        this.totalElements = response.totalElements;
      });
  }

  fetchCategories() {
    this.newsService.getCategoriesFromNews().subscribe((response: any) => {
      this.categories = response;
    });
  }

  fetchNewsByCategory() {
    this.currentView = 'category';
    this.newsService.getNewsByCategoryWithPagination(this.currUserInterest, this.pageIndex, this.pageSize)
      .subscribe((response: any) => {
        this.newsList = response.content;
        this.totalPages = response.totalPages;
        this.totalElements = response.totalElements;
      });
  }

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.pageIndex = newPage;
      this.updatePageRange(); // Update the visible page range
      if (this.currentView === 'all') {
        this.newsService
          .getPaginatedNews(this.pageIndex, this.pageSize)
          .subscribe((response: any) => {
            this.newsList = response.content;
            this.totalPages = response.totalPages;
            this.totalElements = response.totalElements;
          });
      } else {
        this.fetchNewsByCategory();
      }
    }
  }

  updatePageRange(): void {
    const totalPagesToShow = 7;
    const edgeCount = 2;
    this.pageRange = [];

    if (this.totalPages <= totalPagesToShow) {
      for (let i = 0; i < this.totalPages; i++) {
        this.pageRange.push(i);
      }
    } else {
      if (this.pageIndex < totalPagesToShow - edgeCount - 1) {

        for (let i = 0; i < totalPagesToShow - 1; i++) {
          this.pageRange.push(i);
        }
        this.pageRange.push(-1);
      } else if (
        this.pageIndex >
        this.totalPages - (totalPagesToShow - edgeCount)
      ) {
        this.pageRange.push(-1);
        for (
          let i = this.totalPages - (totalPagesToShow - 1);
          i < this.totalPages;
          i++
        ) {
          this.pageRange.push(i);
        }
      } else {
        this.pageRange.push(-1);
        for (
          let i =
            this.pageIndex - Math.floor((totalPagesToShow - edgeCount - 1) / 2);
          i <=
          this.pageIndex + Math.floor((totalPagesToShow - edgeCount - 1) / 2);
          i++
        ) {
          this.pageRange.push(i);
        }
        this.pageRange.push(-1);
      }
    }
  }

  onPageSizeChange(): void {
    this.pageIndex = 0;
    this.updatePageRange(); // Update the visible page range
    this.fetchNewsByCategory();
  }

  incrementLikes(newsId: any) {
    const newsItem = this.newsList.find(
      (item: { newsId: any }) => item.newsId === newsId
    );
    if (newsItem) {
      newsItem.newsLikes += 1;
    }
    this.newsService.incrementLike(newsId).subscribe(
      (response: any) => { },
      (error) => {
        // Revert the UI update in case of error
        newsItem.newsLikes -= 1;
        console.error('Error incrementing like:', error);
      }
    );
  }

  incrementDislikes(newsId: any) {
    this.newsService.incrementDisLike(newsId).subscribe(
      (updatedNewsItem: any) => {
        const index = this.newsList.findIndex(
          (item: { newsId: any }) => item.newsId === newsId
        );
        if (index !== -1) {
          this.newsList[index].newsDislikes = updatedNewsItem.newsDislikes;
        }
      },
      (error) => {
        console.error('Error incrementing dislike:', error);
      }
    );
  }

  updateHistory(userId: number, newsId: number) {
    this.newsService.updateHistory(userId, newsId).subscribe({
      next: () => console.log('User history updated successfully'),
    });
  }

  stay() {
    this.router.navigate(['homepage', this.currUserId, this.currUserInterest]);
  }

  navigateToCategory(category: any) {
    this.router.navigate(['homepage', this.currUserId, category]);
  }

  clkHistory() {
    this.router.navigate([
      '/newsHistory',
      this.currUserId,
      this.currUserInterest,
    ]);
  }

  clkSort() {
    this.router.navigate(['/sortNews']);
  }

  clkSearch() {
    this.router.navigate(['/searchNewsByAuthor', this.currUserId, this.currUserInterest, this.currUserSubscriptionStatus]);
  }

  clkSubscribe() {
    this.router.navigate(['/subscribe', this.currUserId]);
  }

  clkProfile() {
    this.router.navigate(['/profile', this.currUserId]);
  }

  selectCategory(category: string) {
    this.navigateToCategory(category);
    this.currUserInterest = category; // Update the current user interest
    this.pageIndex = 0; // Reset to the first page for the new category
    this.fetchNewsByCategory();
    // Perform any actions based on the selected category
  }

  redirectToGmail(): void {
    this.stay();
    const email = 'dirandevaiahkj16@gmail.com';
    const subject = 'Inquiry About News Website Features';
    const body =
      'Hello,\n\nI am a user of your news website, and I have a few questions/suggestions regarding its features.\n\n1. Are there any plans to add more news categories?\n2. I would love to see an option to filter news based on topics or regions.\n3. Is it possible to add a notification feature for breaking news?\n\nLooking forward to hearing from you.\n\nThank you!\n\nBest regards,\n[Your Name]';
    // Open Gmail compose page
    window.open(
      `https://mail.google.com/mail/?view=cm&fs=1&to=${encodeURIComponent(
        email
      )}&su=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}`,
      '_blank'
    );
    this.router.navigate(['homepage', this.currUserId, this.currUserInterest]);
  }
}
