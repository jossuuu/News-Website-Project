import { Component, OnInit } from '@angular/core';
import { News } from '../news';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../admin.service';
import { CountryService } from '../country.service';

@Component({
  selector: 'app-update-news-form',
  templateUrl: './update-news-form.component.html',
  styleUrl: './update-news-form.component.css'
})
export class UpdateNewsFormComponent implements OnInit {
  newsList: any;
  newsId: any;
  categoryList: any;


  constructor(private adminService: AdminService, private router: Router, private activatedRoute: ActivatedRoute) { }


  ngOnInit(): void {
    this.newsId = this.activatedRoute.snapshot.params['newsId'];

    this.adminService.getCategoriesFromNews().subscribe((response: any) => {
      this.categoryList = response;
    })

    this.adminService.getNewsById(this.newsId).subscribe((response: any) => {
      this.newsList = response;
      if (this.newsList.newsPublishedAT) {      //to change the format of date to yyyy-mm-dd
        const dateParts = this.newsList.newsPublishedAT.split('-');
        if (dateParts.length === 3) {
          const formattedDate = `${dateParts[2]}-${dateParts[1]}-${dateParts[0]}`;
          this.newsList.newsPublishedAT = formattedDate;
        }
      }
    });
  }

  onEdit() {
    this.adminService.updateNewsById(this.newsId, this.newsList).subscribe((response: any) => {
      alert("News is Updated");
      this.router.navigate(['/updateNews'])
    });
  }


  goBack() {
    this.router.navigate(['/updateNews']);
  }
}
