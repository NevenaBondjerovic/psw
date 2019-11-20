import { Component, OnInit } from '@angular/core';
import {FormGroup} from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationUrl: string = 'http://localhost:8080/users/register';
  errorMessage = null;

  email: string;
  password: string;
  confirmPassword: string;
  name: string;
  surname: string;
  address: string;
  city: string;
  state: string;
  phoneNumber: string;
  insuranceNumber: string;
  registrationRequest: {};

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  onSubmit() {
    this.registrationRequest = {
      username: this.email,
      password: this.password,
      confirmPassword: this.confirmPassword,
      name: this.name,
      surname: this.surname,
      address: this.address,
      city: this.city,
      state: this.state,
      phoneNumber: this.phoneNumber,
      insuranceNumber: this.insuranceNumber,
      approvedByAdministrator: false,
      activated: false
    };
    this.http.post(this.registrationUrl, this.registrationRequest)
    .subscribe(responseData => {
      console.log(responseData);
      this.errorMessage = null;
    }, error => {
      this.errorMessage = error.error.message;
    });
  }

}
