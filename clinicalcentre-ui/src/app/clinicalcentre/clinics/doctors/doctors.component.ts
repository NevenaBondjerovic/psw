import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Doctor } from 'src/app/clinicalcentre/clinics/doctors/doctor';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {

  clinicId: number;
  date: string;
  type: string;
  doctors: Array<Doctor>;
  originalListOfDoctors: Array<Doctor>;
  selectedDoctor = null;
  stars: any[] = [];
  filtered: boolean = false;

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
    this.date = this.date === "null" ? null : this.date;
    this.type = this.route.snapshot.paramMap.get("type");
    this.type = this.type === "null" ? null : this.type;
    this.http.post('http://localhost:8080/appointments/doctors', {
      clinicId: this.clinicId,
      date: this.date,
      type: this.type
    })
    .subscribe((responseData: Array<Doctor>) => {
      this.doctors = responseData;
      this.originalListOfDoctors = responseData;
      this.filtered = true;
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

  filter(filterName, filterScore){
    this.doctors = this.originalListOfDoctors.filter((doctor) => {
      return (this.checkNameAndSurname(filterName, doctor) && this.checkScore(filterScore, doctor));
    });
  }

  checkNameAndSurname(name, doctor){
    if(name === '' || name === undefined){
      return true;
    }else{
      return (doctor.doctorName.toUpperCase().includes(name.toUpperCase()))
        || (doctor.doctorSurname.toUpperCase().includes(name.toUpperCase()));
    }
  }

  checkScore(score, doctor){
    if(score === undefined || score === ''){
      return true;
    }else {
      return doctor.score == score;
    }
  }

}
