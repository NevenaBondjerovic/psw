import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password: string = '';
  usersUrl: string = 'http://localhost:8080/users/';
  errorMessage = null;

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  onLogin(){
    this.http.post(this.usersUrl, {username: this.username, password: this.password})
      .subscribe(responseData => {
        console.log(responseData);
        this.errorMessage = null;
      }, error => {
        this.errorMessage = error.error.message;
      });
  }

}
