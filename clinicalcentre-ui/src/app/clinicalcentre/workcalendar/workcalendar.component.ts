import { Component, OnInit } from '@angular/core';
import { Appointment } from 'src/app/clinicalcentre/appointments/appointment';
import { HttpClient } from '@angular/common/http';
import { GlobalVariablesService } from 'src/app/global-variables.service';

@Component({
  selector: 'app-workcalendar',
  templateUrl: './workcalendar.component.html',
  styleUrls: ['./workcalendar.component.css']
})
export class WorkcalendarComponent implements OnInit {

  appointments: [Appointment];
  selectedAppointment: Appointment;
  appointmentsUrl: string = 'http://localhost:8080/appointments';
  finalPrice: number = null;
  loadingData: boolean = false;

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';


  constructor(private http: HttpClient,
      private globalVariables: GlobalVariablesService) {
    this.loadingData = true;
    this.selectedAppointment = null;
    this.http.get(this.appointmentsUrl + "/doctor/" + this.globalVariables.loggedInUser.id)
    .subscribe((responseData: [Appointment]) => {
      this.appointments = responseData;
      this.loadingData = false;
    }, error => {
      this.handleError(error);
    });
   }

  ngOnInit() {
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

  onSelect(appointment){
    this.loadingData = true;
    this.http.get(this.appointmentsUrl + "/" + appointment.id)
    .subscribe((responseData: Appointment) => {
      this.selectedAppointment = responseData;
      this.loadingData = false;
      this.finalPrice = this.selectedAppointment.pricelist.price
          - (this.selectedAppointment.pricelist.price * this.selectedAppointment.pricelist.discount / 100);
    }, error => {
      this.handleError(error);
    });
  }
}
