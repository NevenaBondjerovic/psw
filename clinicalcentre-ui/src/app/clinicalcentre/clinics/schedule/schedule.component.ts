import { Component, OnInit } from '@angular/core';
import { Appointment } from 'src/app/clinicalcentre/appointments/appointment';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { GlobalVariablesService } from 'src/app/global-variables.service';
import { User } from 'src/app/clinicalcentre/user';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

  selectedAppointment: Appointment;
  appointmentsUrl: string = 'http://localhost:8080/appointments';
  requestsUrl: string = 'http://localhost:8080/appointmentrequests';
  finalPrice: number = null;
  loadingData: boolean = false;
  loggedInUser: User = null;
  requestProcessed: boolean = false;

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';

  constructor(private http: HttpClient, private route:ActivatedRoute,
        private globalVariables: GlobalVariablesService) {
    this.loggedInUser = this.globalVariables.loggedInUser;
   }

  ngOnInit() {
    this.loadingData = true;
    this.http.get(this.appointmentsUrl + "/" + (+this.route.snapshot.paramMap.get("appointmentId")))
    .subscribe((responseData: Appointment) => {
      this.selectedAppointment = responseData;
      this.loadingData = false;
      this.finalPrice = this.selectedAppointment.pricelist.price
          - (this.selectedAppointment.pricelist.price * this.selectedAppointment.pricelist.discount / 100);
    }, error => {
      this.handleError(error);
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

  onSubmit(){
    this.loadingData = true;
    this.http.post(this.requestsUrl,
          { appointmentId: this.selectedAppointment.id , userId: this.loggedInUser.id } )
    .subscribe(() => {
      this.loadingData = false;
      this.selectedAppointment = null;
      this.requestProcessed = true;
    }, error => {
      this.handleError(error);
    });
  }


}
