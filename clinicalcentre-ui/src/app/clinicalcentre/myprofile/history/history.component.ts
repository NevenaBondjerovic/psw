import { Component, OnInit } from '@angular/core';
import { GlobalVariablesService } from 'src/app/global-variables.service';
import { User } from 'src/app/clinicalcentre/user';
import { Appointment } from 'src/app/clinicalcentre/appointments/appointment';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  loggedInUser: User = null;
   appointmentUrl: string = 'http://localhost:8080/appointments/user';
   stars: any[] = [];
   scores: Array<{
      appointmentDTO: Appointment,
      scoreForDoctor: number,
      scoreForClinic: number,
      givenDoctorScore: number,
      givenClinicScore: number
    }> = [];
   selectedScore: {
      appointmentDTO: Appointment,
      scoreForDoctor: number,
      scoreForClinic: number
    } = null;
    doctorScore: number = null;
    clinicScore: number = null;

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';

  constructor(private http: HttpClient, private globalVariables: GlobalVariablesService) {
    this.loggedInUser = this.globalVariables.loggedInUser;
    this.http.get(this.appointmentUrl + "/" + this.loggedInUser.id)
    .subscribe((responseData: Array<{
      appointmentDTO: Appointment,
      scoreForDoctor: number,
      scoreForClinic: number,
      givenDoctorScore: number,
      givenClinicScore: number
    }>) => {
      this.scores = responseData;
    }, error => {
      this.handleError(error);
    });
  }

  ngOnInit() {
  }

  addScore(score){
    this.selectedScore = score;
  }

  submitScore(score){
    console.log(score);
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

  convertScoreToStars(score){
    this.stars = [];
    for(let i=1;i<=score;i++){
      this.stars.push(i);
    }
    return this.stars;
  }
}
