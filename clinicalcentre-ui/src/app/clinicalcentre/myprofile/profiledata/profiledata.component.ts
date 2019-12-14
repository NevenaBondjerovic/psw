import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/clinicalcentre/user';

@Component({
  selector: 'app-profiledata',
  templateUrl: './profiledata.component.html',
  styleUrls: ['./profiledata.component.css']
})
export class ProfiledataComponent implements OnInit {

  profileData: User = null;
  userUrl: string = 'http://localhost:8080/users';
  username: string = 'admin@admin.com';
  newPasswordData: {
    oldPassword: string;
    newPassword: string;
    confirmNewPassword: string;
  };
  requestedChangePassword: Boolean = false;
  registrationSuccessful: Boolean = false;
  processingData: Boolean = false;

  errorMessage = null;
  defaultErrorMessage = 'Please enter valid data.';
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';

  constructor(private http: HttpClient) {
    this.http.get(this.userUrl + '/myprofile/' + this.username)
    .subscribe((responseData: User) => {
      this.resetNewPasswordData();
      this.profileData = responseData;
    }, error => {
      this.handleError(error);
      this.profileData = null;
    });
  }

  ngOnInit() {
  }

  onChangePassword() {
    this.resetNewPasswordData();
    this.requestedChangePassword = !this.requestedChangePassword;
  }

  onSubmit(form: NgForm) {
    this.processingData = true;
    if(form.valid && this.checkPasswordValidity(form)){
      if(this.requestedChangePassword){
        this.profileData.password = this.newPasswordData.newPassword;
      }
      this.http.put(this.userUrl, this.profileData)
      .subscribe((responseData: User) => {
        this.resetNewPasswordData();
        this.requestedChangePassword = false;
        this.errorMessage = null;
        this.registrationSuccessful = true;
        this.profileData = responseData;
        this.processingData = false;
      }, error => {
        this.handleError(error);
        this.profileData = null;
        this.processingData = false;
      });
    } else {
      this.errorMessage = this.defaultErrorMessage;
    }
  }

  handleError(error){
      if(error.status === this.httpStatusServerNotAvailable){
        this.errorMessage = this.serviceNotAvailableErrorMessage;
      } else if (error.status === this.httpStatusInternalServerError){
        this.errorMessage = this.serverErrorMessage;
      } else {
        this.errorMessage = error.error.message;
      }
  }

  resetNewPasswordData(){
    this.newPasswordData = {
      oldPassword: undefined,
      newPassword: undefined,
      confirmNewPassword: undefined
    };
  }

  checkPasswordValidity(form: NgForm){
    return !this.requestedChangePassword ||
            (this.newPasswordData.oldPassword===this.profileData.password
              && this.newPasswordData.newPassword===this.newPasswordData.confirmNewPassword);
  }

}
