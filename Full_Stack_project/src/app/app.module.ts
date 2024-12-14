import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HomepageComponent } from './homepage/homepage.component';
import { AdminComponent } from './admin/admin.component';
import { AddNewsComponent } from './add-news/add-news.component';
import { DeleteNewsComponent } from './delete-news/delete-news.component';
import { UpdateNewsComponent } from './update-news/update-news.component';
import { UpdateNewsFormComponent } from './update-news-form/update-news-form.component';
import { NewsHistoryComponent } from './news-history/news-history.component';
import { DisplayLikesAndDislikesComponent } from './display-likes-and-dislikes/display-likes-and-dislikes.component';
import { DisplayUserDetailsComponent } from './display-user-details/display-user-details.component';
import { SortingComponent } from './sorting/sorting.component';
import { SearchByAuthorComponent } from './search-by-author/search-by-author.component';
import { SubscribeComponent } from './subscribe/subscribe.component';
import { ProfileComponent } from './profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomepageComponent,
    AdminComponent,
    AddNewsComponent,
    DeleteNewsComponent,
    UpdateNewsComponent,
    UpdateNewsFormComponent,
    NewsHistoryComponent,
    DisplayLikesAndDislikesComponent,
    DisplayUserDetailsComponent,
    SortingComponent,
    SearchByAuthorComponent,
    SubscribeComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
