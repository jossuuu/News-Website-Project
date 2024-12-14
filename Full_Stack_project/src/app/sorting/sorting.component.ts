import { Component } from '@angular/core';
import { NewsService } from '../news.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sorting',
  templateUrl: './sorting.component.html',
  styleUrl: './sorting.component.css'
})
export class SortingComponent {
  field: any;
  newsList: any;
  pageIndex: any = 0;
  pageSize: any = 4;
  pageRange: number[] = [];
  totalPages: any = 0;
  totalElements: any = 0;

  constructor(private newsService: NewsService, private router: Router) { }

  sortUsingField(field: any) {
    this.newsService.sortByNews(field, this.pageIndex, this.pageSize).subscribe((response: any) => {
      this.newsList = response.content;
      this.totalPages = response.totalPages;
      this.totalElements = response.totalElements;
    })
  }

  goBack() {
    this.router.navigate([''])
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
