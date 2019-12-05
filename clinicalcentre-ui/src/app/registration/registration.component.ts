import { Component, OnInit } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationUrl: string = 'http://localhost:8080/users/register';
  errorMessage = null;
  registrationRequest: {};
  defaultErrorMessage = 'Please enter valid data.';
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusConflict = 409;
  httpStatusInternalServerError = 500;
  httpStatusServerNotAvailable = 0;
  registrationSuccessful = false;

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  onSubmit(form: NgForm) {
    this.registrationSuccessful = false;
    if(form.valid && form.value.password===form.value.confirmPassword){
      this.registrationRequest = {
        username: form.value.email,
        password: form.value.password,
        name: form.value.name,
        surname: form.value.surname,
        address: form.value.address,
        city: form.value.city,
        state: form.value.state,
        phoneNumber: form.value.phoneNumber,
        insuranceNumber: form.value.insuranceNumber,
        activated: false
      };
      this.http.post(this.registrationUrl, this.registrationRequest)
      .subscribe(responseData => {
        console.log(responseData);
        this.errorMessage = null;
        this.registrationSuccessful = true;
      }, error => {
        if(error.status === this.httpStatusConflict){
          this.errorMessage = error.error.message;
        } else if (error.status === this.httpStatusInternalServerError) {
          this.errorMessage = this.serverErrorMessage;
        } else if (error.status === this.httpStatusServerNotAvailable) {
          this.errorMessage = this.serviceNotAvailableErrorMessage;
        } else {
          this.errorMessage = this.defaultErrorMessage;
        }
      });
    }else{
      this.errorMessage = this.defaultErrorMessage;
    }
  }

}
