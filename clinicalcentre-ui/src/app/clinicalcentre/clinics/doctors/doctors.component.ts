import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {

  doctors: any[];
  selectedDoctor = null;
   stars: any[] = [];

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';

  constructor(private http: HttpClient) {
    this.http.post('http://localhost:8080/appointments/doctors', {
      clinicId: 1,
      date: '2020-02-26',
      type: 'General medical examination'
    })
    .subscribe((responseData: any[]) => {
      this.doctors = responseData;
      console.log(responseData);
    }, error => {
      this.handleError(error);
    });
  }

  ngOnInit() {
  }

  onSelect(doctor){
    this.errorMessage = null;
    this.selectedDoctor = doctor;
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
