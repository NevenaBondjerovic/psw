import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { GlobalVariablesService } from 'src/app/global-variables.service';

@Component({
  selector: 'app-activationpage',
  templateUrl: './activationpage.component.html',
  styleUrls: ['./activationpage.component.css']
})
export class ActivationpageComponent implements OnInit {

  id: string = null;
  username: string = '';
  password: string = '';
  usersUrl: string = 'http://localhost:8080/users/';
  errorMessage: string = null;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';
  httpStatusServerNotAvailable = 0;

  constructor(private _Activatedroute:ActivatedRoute,
    private http: HttpClient, private router: Router, private globalVariables: GlobalVariablesService) {
  }

  ngOnInit() {
   this._Activatedroute.paramMap.subscribe(params => {
       this.id = params.get('id');
       this.http.get(this.usersUrl + this.id )
       .subscribe(() => {}, error => {
          this.router.navigate(['/login']);
       });
   });
  }

  onSubmit(){
    this.http.post(this.usersUrl + 'activate', {id: this.id, username: this.username, password: this.password})
    .subscribe((responseData: User) => {
      this.globalVariables.loggedInUser = responseData;
      this.router.navigate(['/clinicalcentre/home']);
    }, error => {
      if (error.status === this.httpStatusServerNotAvailable) {
        this.errorMessage = this.serviceNotAvailableErrorMessage;
      } else {
        this.errorMessage = error.error.message;
      }
    });
  }

}
