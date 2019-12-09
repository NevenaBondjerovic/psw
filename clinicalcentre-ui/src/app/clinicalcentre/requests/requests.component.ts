import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  requests: any[];
  selectedRequest: {
    username: string,
    name: string,
    surname: string,
    address: string,
    city: string,
    state: string,
    phoneNumber: string,
    insuranceNumber: string
   };
  rejected: Boolean = false;
  registrationUrl: string = 'http://localhost:8080/registration';
  acceptRejectRequest: {};
  processingRequest: Boolean = false;
  requestProcessed: Boolean = false;

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';

  constructor(private http: HttpClient) {
    this.selectedRequest = null;
    this.http.get(this.registrationUrl)
    .subscribe((responseData: { userData: any[]}) => {
      this.requests = responseData.userData;
    }, error => {
      this.handleError(error);
      this.requests = [];
    });
  }

  ngOnInit() {
  }

  onSelect(request){
    this.errorMessage = null;
    this.selectedRequest = request;
    this.requestProcessed = false;
  }

  onAccept() {
  this.processingRequest = true;
    this.acceptRejectRequest = {
      username: this.selectedRequest.username,
      approved: true,
      declineReason: null
    };
    this.http.post(this.registrationUrl + '/requests', this.acceptRejectRequest)
    .subscribe(responseData => {
      this.requests.splice(this.requests.indexOf(this.selectedRequest), 1);
      this.requestProcessed = true;
      this.selectedRequest = null;
      this.processingRequest = false;
    }, error => {
      this.handleError(error);
      this.processingRequest = false;
    });
  }

  onReject() {
    this.rejected = true;
  }

  onReasonSubmit(reasonInput) {
    this.processingRequest = true;
    this.acceptRejectRequest = {
      username: this.selectedRequest.username,
      approved: false,
      declineReason: reasonInput
    };
    this.http.post(this.registrationUrl + '/requests', this.acceptRejectRequest)
    .subscribe(responseData => {
      this.rejected = false;
      this.requestProcessed = true;
      this.requests.splice(this.requests.indexOf(this.selectedRequest), 1);
      this.selectedRequest = null;
      this.processingRequest = false;
    }, error => {
      this.handleError(error);
      this.processingRequest = false;
    });
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

}
