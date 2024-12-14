import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-display-user-details',
  templateUrl: './display-user-details.component.html',
  styleUrl: './display-user-details.component.css'
})
export class DisplayUserDetailsComponent {
  users: any;
  defaultStatus: String = "false";

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((response: any) => {
      this.users = response;
    })
  }
  goBack() {
    this.router.navigate(['/admin']);
  }

  changeSubStatus(userId: any, status:any){
    this.userService.changeUserSubStatus(userId, status).subscribe((response:any)=>{
      this.users = response;
    })
  }

}
