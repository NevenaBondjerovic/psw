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

  constructor(private http: HttpClient) {
    this.selectedRequest = null;
    this.http.get(this.registrationUrl)
    .subscribe((responseData: { userData: any[]}) => {
      this.requests = responseData.userData;
    }, error => {
      console.log(error);
      this.requests = [];
    });
  }

  ngOnInit() {
  }

  onSelect(request){
    this.selectedRequest = request;
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
      this.selectedRequest = null;
      this.processingRequest = false;
    }, error => {
      console.log(error);
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
      this.requests.splice(this.requests.indexOf(this.selectedRequest), 1);
      this.selectedRequest = null;
      this.processingRequest = false;
    }, error => {
      console.log(error);
      this.processingRequest = false;
    });
  }

}
