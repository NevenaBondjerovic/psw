import { Component, OnInit } from '@angular/core';
import { Appointment } from 'src/app/clinicalcentre/appointments/appointment';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {

  appointments: [Appointment];
  selectedAppointment: Appointment;
  appointmentsUrl: string = 'http://localhost:8080/appointments';

  loadingData: boolean = false;

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';


  constructor(private http: HttpClient) {
    this.loadingData = true;
    this.selectedAppointment = null;
    this.http.get(this.appointmentsUrl)
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
    console.log(appointment);
  }

}
