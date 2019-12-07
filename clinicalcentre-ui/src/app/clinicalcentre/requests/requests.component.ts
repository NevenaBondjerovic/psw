import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  requests;
  selectedRequest: {} = null;
  reason: string = null;
  rejected: Boolean = false;

  constructor() {
    this.requests = [
      {userData: {username: 'user@user1', name: 'Pera', surname: 'Peric'}},
      {userData: {username: 'user@user2', name: 'Mika', surname: 'Peric'}},
      {userData: {username: 'user@user3', name: 'Mika', surname: 'Peric'}},
      {userData: {username: 'user@user4', name: 'Mika', surname: 'Peric'}}
    ];
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
