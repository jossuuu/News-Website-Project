import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../login.service';
import { User } from '../user';

@Component({
  selector: 'app-login-signup',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  user: User = new User(0, "", "", "", "", "", ""); // Single instance for both login and signup
  userId: any;
  userInterest: any;
  userSubscribe: any;

  isSignup: boolean = false; // Toggle between login and signup forms


  constructor(private authService: AuthService, private router: Router) { }

  onLogin() {
    this.authService.login(this.user).subscribe(
      (response: any) => {
        if (response != null) {
          const userRole = response.userRole;
          this.userId = response.usersId;
          this.userInterest = response.userInterest;
          this.userSubscribe = response.subscriptionStatus;

          // userRole condition assign another user reference variable and call userRole
          alert('Login Succesful');
          if (userRole === 'admin') {
            this.router.navigate(['admin'])
          } else if (userRole === 'user') {
            this.router.navigate(['homepage', this.userId, this.userInterest, this.userSubscribe]);
          }
          else {
            alert("Invalid Role detected");
          }
        }
        else {
          alert("Invalid Login");
        }
      },
      (error) => {
        alert('An error occurred during login.');
      }
    );
  }

  onSignup() {
    this.authService.signup(this.user).subscribe(
      (response: any) => {
        alert('Signup successful! Please log in.');
        this.goBack();
      },
      (error) => {
        alert('An error occurred during signup.');
      }
    );
  }

  toggleForm(): void {
    this.isSignup = !this.isSignup; // Toggle between login and signup
  }

  goBack(): void {
    this.user = new User(0, "", "", "", "", "", "");
    this.isSignup = false;
  }
}
