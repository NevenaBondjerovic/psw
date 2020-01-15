import { Component, OnInit } from '@angular/core';
import { GlobalVariablesService } from 'src/app/global-variables.service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-clinicalcentre',
  templateUrl: './clinicalcentre.component.html',
  styleUrls: ['./clinicalcentre.component.css']
})
export class ClinicalcentreComponent implements OnInit {

  email: String = '';

  constructor(private router: Router, private globalVariables: GlobalVariablesService) {
    if(this.globalVariables.loggedInUser === null){
        this.router.navigate(['/login']);
    }
    this.email = this.globalVariables.loggedInUser.username;
   }

  ngOnInit() {
  }

}
