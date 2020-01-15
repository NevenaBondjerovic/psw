import { Component, OnInit } from '@angular/core';
import { GlobalVariablesService } from 'src/app/global-variables.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  userType: String = '';

  constructor(private globalVariables: GlobalVariablesService) {
    this.userType = this.globalVariables.loggedInUser.userType;
  }

  ngOnInit() {
  }

}
