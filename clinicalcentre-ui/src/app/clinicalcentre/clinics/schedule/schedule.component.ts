import { Component, OnInit } from '@angular/core';
import { Appointment } from 'src/app/clinicalcentre/appointments/appointment';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {

  selectedAppointment: Appointment;
  appointmentsUrl: string = 'http://localhost:8080/appointments';
  finalPrice: number = null;
  loadingData: boolean = false;

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';

  constructor(private http: HttpClient, private route:ActivatedRoute) { }

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

  }


}
