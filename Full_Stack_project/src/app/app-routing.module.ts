import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
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

const routes: Routes = [
  { path: "", component: LoginComponent },
  { path: "homepage/:userId/:userInterest/:subscriptionStatus", component: HomepageComponent },
  { path: "admin", component: AdminComponent },
  { path: "deleteNews", component: DeleteNewsComponent },
  { path: "updateNews", component: UpdateNewsComponent },
  { path: "form/:newsId", component: UpdateNewsFormComponent },
  { path: "newsHistory/:userId/:userInterest", component: NewsHistoryComponent },
  { path: "displayLikesAndDislikes", component: DisplayLikesAndDislikesComponent },
  { path: "displayUserDetails", component: DisplayUserDetailsComponent },
  { path: "sortNews", component: SortingComponent },
  { path: "subscribe/:userId", component: SubscribeComponent },
  { path: "profile/:userId", component: ProfileComponent },
  { path: "searchNewsByAuthor/:userId/:userInterest/:subscriptionStatus", component: SearchByAuthorComponent },
  { path: "news", component: AddNewsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
