import { Component, OnInit } from '@angular/core';
import { News } from '../news';
import { AdminService } from '../admin.service';
import { Router } from '@angular/router';
import { CountryService } from '../country.service';

@Component({
  selector: 'app-add-news',
  templateUrl: './add-news.component.html',
  styleUrl: './add-news.component.css'
})
export class AddNewsComponent implements OnInit {

  countries: any[] = [];
  categoryList: any;

  news: News = new News(0, "", "", "", "", "", "", "", "", "", "", 0, 0, "");
  constructor(private adminService: AdminService, private router: Router, private countryService: CountryService) { }


  ngOnInit(): void {
    this.countryService.getCountry().subscribe((response: any) => {
      this.countries = response.map((country: any) => country.name.common);
    });

    this.adminService.getCategoriesFromNews().subscribe((response: any) => {
      this.categoryList = response;
    })
  }

  onSubmit() {
    this.adminService.addNews(this.news).subscribe((response: any) => {
      alert("News Added Succesfully");
      this.clear();
    }, (error) => {
      alert("Something went wrong");
    });

  }

  goBack() {
    this.router.navigate(['/admin']);
  }

  clear(): void {
    this.news = {
      newsId: 0,
      newsCategory: "",
      newsTitle: "",
      newsDescription: "",
      newsContent: "",
      newsURL: "",
      newsImage: "",
      newsPublishedAt: "",
      newsAuthor: "",
      newsSource: "",
      newsCountry: "",
      newsLikes: 0,
      newsDislikes: 0,
      newsPremium: ""
    };
    this.router.navigate(['/news']);
  }
}
