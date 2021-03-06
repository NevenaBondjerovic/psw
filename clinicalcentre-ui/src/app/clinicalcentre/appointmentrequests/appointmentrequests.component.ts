import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVariablesService } from 'src/app/global-variables.service';
import { Appointment } from 'src/app/clinicalcentre/appointments/appointment';
import { User } from 'src/app/clinicalcentre/user';

@Component({
  selector: 'app-appointmentrequests',
  templateUrl: './appointmentrequests.component.html',
  styleUrls: ['./appointmentrequests.component.css']
})
export class AppointmentrequestsComponent implements OnInit {

  requests: any[];
  selectedRequest: {
    id: number,
    appointment: Appointment,
    adminId: number,
    user: User
  };
  successMessage: string = null;
  requestsUrl: string = 'http://localhost:8080/appointmentrequests';
  approveUrl: string = 'http://localhost:8080/appointmentrequests/approve';
  processingRequest: boolean = false;

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';

  constructor(private http: HttpClient, private globalVariables: GlobalVariablesService) {
    this.selectedRequest = null;
    this.http.get(this.requestsUrl + "/" + this.globalVariables.loggedInUser.id)
    .subscribe((responseData: any[]) => {
      this.requests = responseData;
    }, error => {
      this.handleError(error);
      this.requests = [];
    });
   }

  ngOnInit() {
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

  onSelect(request){
    this.successMessage = null;
    this.errorMessage = null;
    this.selectedRequest = request;
  }

  onAccept(){
    this.processingRequest = true;
    this.http.put(this.approveUrl, {requestId: this.selectedRequest.id, approved: true})
     .subscribe(() => {
      this.successMessage = 'The request is successfully accepted. ';
      this.requests.splice(this.requests.indexOf(this.selectedRequest), 1);
      this.selectedRequest = null;
      this.processingRequest = false;
     }, error => {
       this.handleError(error);
       this.processingRequest = false;
     });
  }

  onReject(){
    this.processingRequest = true;
    this.http.put(this.approveUrl, {requestId: this.selectedRequest.id, approved: false})
     .subscribe(() => {
      this.successMessage = 'The request is successfully rejected. ';
      this.requests.splice(this.requests.indexOf(this.selectedRequest), 1);
      this.selectedRequest = null;
      this.processingRequest = false;
     }, error => {
       this.handleError(error);
       this.processingRequest = false;
     });
  }

}
