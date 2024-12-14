import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NewsService } from '../news.service';
import { News } from '../news';

@Component({
  selector: 'app-update-news',
  templateUrl: './update-news.component.html',
  styleUrl: './update-news.component.css'
})
export class UpdateNewsComponent implements OnInit {
  newsList: any;
  pageIndex: any = 0;
  pageSize: any = 4;
  pageRange: number[] = [];
  totalPages: any = 0;
  totalElements: any = 0;
  defaultPremium: String = "true";
  news: News = new News(0, "", "", "", "", "", "", "", "", "", "", 0, 0, "");

  constructor(private adminService: AdminService, private router: Router, private newsService: NewsService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.newsService.getPaginatedNews(this.pageIndex, this.pageSize).subscribe((response: any) => {
      this.newsList = response.content;
      this.totalPages = response.totalPages;
      this.totalElements = response.totalElements;
    })
  }

  onUpdate(newsId: any) {
    this.router.navigate(['/form', newsId]);
  }


  goBack() {
    this.router.navigate(['/admin']);
  }

  onUpdatePremium(newsId: any, premium: any) {
    this.adminService.setNewsPremium(newsId, premium).subscribe((response: any) => {
      this.newsList = response;
    })
  }

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.pageIndex = newPage;
      this.updatePageRange(); // Update the visible page range
      this.newsService
        .getPaginatedNews(this.pageIndex, this.pageSize)
        .subscribe((response: any) => {
          this.newsList = response.content;
          this.totalPages = response.totalPages;
          this.totalElements = response.totalElements;
        });
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
    this.updatePageRange();
  }
}
