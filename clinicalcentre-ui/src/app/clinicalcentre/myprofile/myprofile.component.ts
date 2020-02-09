import { Component, OnInit } from '@angular/core';
import { GlobalVariablesService } from 'src/app/global-variables.service';
import { User } from 'src/app/clinicalcentre/user';

@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.css']
})
export class MyprofileComponent implements OnInit {

  loggedInUser: User = null;

  constructor(private globalVariables: GlobalVariablesService) {
    this.loggedInUser = this.globalVariables.loggedInUser;
  }

  ngOnInit() {
  }

}
