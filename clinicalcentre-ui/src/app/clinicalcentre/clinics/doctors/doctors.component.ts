import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {

  clinicId: number;
  date: string;
  type: string;
  doctors: any[];
  selectedDoctor = null;
   stars: any[] = [];

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';

  constructor(private http: HttpClient, private route:ActivatedRoute) {

  }

  ngOnInit() {
    this.clinicId = +this.route.snapshot.paramMap.get("clinicId");
    this.date = this.route.snapshot.paramMap.get("date");
    this.type = this.route.snapshot.paramMap.get("type");
    this.http.post('http://localhost:8080/appointments/doctors', {
      clinicId: this.clinicId,
      date: this.date,
      type: this.type
    })
    .subscribe((responseData: any[]) => {
      this.doctors = responseData;
    }, error => {
      this.handleError(error);
    });
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
