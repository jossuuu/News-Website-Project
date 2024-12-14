import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../admin.service';
import { NewsService } from '../news.service';

@Component({
  selector: 'app-delete-news',
  templateUrl: './delete-news.component.html',
  styleUrl: './delete-news.component.css'
})
export class DeleteNewsComponent implements OnInit {
  newsList: any;

  constructor(private adminService: AdminService, private router: Router, private newsService: NewsService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.newsService.getAllNews().subscribe((response: any) => {
      this.newsList = response;
    })
  }

  onDelete(newsId: any) {
    this.adminService.deleteNewsById(newsId).subscribe((response: any) => {
      alert("News Deleted Successfully");
      this.newsList = response;
    }, (error) => {
      alert("Something went wrong");
    });
  }

  goBack() {
    this.router.navigate(['/admin']);
  }
}
