import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  requests: any[];
  selectedRequest: {} = null;
  reason: string = null;
  rejected: Boolean = false;
  registrationUrl: string = 'http://localhost:8080/registration';

  constructor(private http: HttpClient) {
    this.http.get(this.registrationUrl)
    .subscribe(responseData => {
      this.requests = responseData.userData;
    }, error => {
      console.log(error);
    });
  }

  ngOnInit() {
  }

  onSelect(request){
    this.selectedRequest = request;
  }

  onAccept() {
    //TBD accept
    this.requests.splice(this.requests.indexOf(this.selectedRequest), 1);
    this.selectedRequest = null;
  }

  onReject() {
    this.rejected = true;
  }

  onReasonSubmit() {
    //TBD reject process
    this.rejected = false;
    this.requests.splice(this.requests.indexOf(this.selectedRequest), 1);
    this.selectedRequest = null;
    this.reason = null;
  }

  isReasonEmpty(){
    console.log(this.reason);
    return this.reason === null || this.reason === undefined || this.reason === '';
  }

}
