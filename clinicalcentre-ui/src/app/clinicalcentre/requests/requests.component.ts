import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {

  requests;
  selectedRequest: {};

  constructor() {
    this.requests = [
      {userData: {username: 'user@user1', name: 'Pera', surname: 'Peric'}},
      {userData: {username: 'user@user2', name: 'Mika', surname: 'Peric'}},
      {userData: {username: 'user@user3', name: 'Mika', surname: 'Peric'}},
      {userData: {username: 'user@user4', name: 'Mika', surname: 'Peric'}}
    ];
    this.selectedRequest = null;
  }

  ngOnInit() {
  }

  onSelect(request){
    console.log(request);
    this.selectedRequest = request;
  }

}
