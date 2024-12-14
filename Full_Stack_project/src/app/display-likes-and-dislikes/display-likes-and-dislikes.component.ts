import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NewsService } from '../news.service';

@Component({
  selector: 'app-display-likes-and-dislikes',
  templateUrl: './display-likes-and-dislikes.component.html',
  styleUrl: './display-likes-and-dislikes.component.css'
})
export class DisplayLikesAndDislikesComponent {

  newsList: any;
  totalPages: any = 0;
  totalElements: any = 0;
  pageIndex: any = 0;
  pageSize: any = 12;
  item: any;

  constructor(private newsService: NewsService, private router: Router) { }

  ngOnInit(): void {
    this.fetchNews();
  }

  fetchNews(): void {
    this.newsService.getPaginatedNews(this.pageIndex, this.pageSize).subscribe((response: any) => {
      this.newsList = response.content;
      this.totalPages = response.totalPages;
      this.totalElements = response.totalElements;
    });
  }

  onPageChange(newPage: any): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.pageIndex = newPage;
      this.fetchNews();
    }
  }

  onPageSizeChange(): void {
    this.pageIndex = 0;
    this.fetchNews();
  }

  goBack() {
    this.router.navigate(['/admin']);
  }
}
