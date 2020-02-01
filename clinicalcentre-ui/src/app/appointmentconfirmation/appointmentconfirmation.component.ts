import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/clinicalcentre/user';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { GlobalVariablesService } from 'src/app/global-variables.service';
import { Appointment } from 'src/app/clinicalcentre/appointments/appointment';

@Component({
  selector: 'app-appointmentconfirmation',
  templateUrl: './appointmentconfirmation.component.html',
  styleUrls: ['./appointmentconfirmation.component.css']
})
export class AppointmentconfirmationComponent implements OnInit {

  loggedInUser: User = null;
  username: string = null;
  password: string = null;
  appointment: Appointment = null;
  finalPrice: number = 0;

  loginUrl: string = 'http://localhost:8080/users/login';
  appointmentsUrl: string = 'http://localhost:8080/appointments';

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';

  constructor(private http: HttpClient, private route:ActivatedRoute,
      private globalVariables: GlobalVariablesService) { }

  ngOnInit() {
  }


  onLogin(){
    this.http.post(this.loginUrl, {username: this.username, password: this.password})
      .subscribe((responseData: User) => {
        if(responseData.activated === true){
          //this.globalVariables.loggedInUser = responseData;
          this.errorMessage = null;
          //this.router.navigate(['/clinicalcentre/home']);
          this.http.get(this.appointmentsUrl + "/" + (+this.route.snapshot.paramMap.get("id")))
           .subscribe((appointmentData: Appointment) => {
            if(appointmentData.scheduledFor.id === responseData.id){
              this.appointment = appointmentData;
              this.finalPrice = this.appointment.pricelist.price
                 - (this.appointment.pricelist.price * this.appointment.pricelist.discount / 100);
              this.loggedInUser = responseData;
            } else{
              this.errorMessage = "Wrong credentials.";
            }
           }, error => {
             this.handleError(error);
           });
        } else {
          //this.globalVariables.loggedInUser = null;
          this.errorMessage = "User is still not activated.";
        }
      }, error => {
        if (error.status === this.httpStatusServerNotAvailable) {
          this.errorMessage = this.serviceNotAvailableErrorMessage;
        } else {
          this.errorMessage = error.error.message;
        }
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
