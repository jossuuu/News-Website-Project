import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
  user: User = new User(0, "", "", "", "", "", "");
  currUserId: any;

  constructor(private userService: UserService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.currUserId = this.activatedRoute.snapshot.params['userId'];
    this.onFetchUserDate(this.currUserId);
  }

  onFetchUserDate(userId: any) {
    this.userService.getUserById(userId).subscribe((response: any) => {
      this.user = response;
    });
  }



  onSubmit(): void {

  }

}
