import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from "@angular/router";
import { User } from 'src/app/clinicalcentre/user';
import { GlobalVariablesService } from 'src/app/global-variables.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password: string = '';
  loginUrl: string = 'http://localhost:8080/users/login';
  errorMessage = null;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';
  httpStatusServerNotAvailable = 0;
  loggedInUser: User = null;


  constructor(private http: HttpClient, private router: Router, private globalVariables: GlobalVariablesService) { }

  ngOnInit() {
  }

  onLogin(){
    this.http.post(this.loginUrl, {username: this.username, password: this.password})
      .subscribe((responseData: User) => {
        this.loggedInUser = responseData;
        this.globalVariables.loggedInUser = responseData;
        this.errorMessage = null;
        this.router.navigate(['/clinicalcentre/home']);
      }, error => {
        if (error.status === this.httpStatusServerNotAvailable) {
          this.errorMessage = this.serviceNotAvailableErrorMessage;
        } else {
          this.errorMessage = error.error.message;
        }
      });
  }

}
