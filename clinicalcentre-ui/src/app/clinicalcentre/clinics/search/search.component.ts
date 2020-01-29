import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormGroup, NgForm } from '@angular/forms';
import { Clinic } from 'src/app/clinicalcentre/clinic';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  allTypesOfAppointments: [string];
  clinics: [{
    appointmentId: number,
    date: string,
    clinicId: number,
    clinicName: string,
    clinicAddress: string,
    clinicScore: number,
    price: number,
    typeName: string
  }];
  stars: any[] = [];
  searched: boolean = false;

  clinicsUrl: string = 'http://localhost:8080/clinics';
  typesUrl: string = 'http://localhost:8080/types';

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';
  formValid: boolean = true;

  constructor(private http: HttpClient) {
    this.http.get(this.typesUrl)
    .subscribe((responseData: [string]) => {
      this.allTypesOfAppointments = responseData;
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

  searchClinics(form: NgForm){
    if(this.isEmpty(form.value.date) || this.isEmpty(form.value.type)){
      this.formValid = false;
    }
    else{
      this.formValid = true;
      this.http.post(this.clinicsUrl + "/search",
          {date: form.value.date, type: form.value.type})
      .subscribe((responseData: [{
          appointmentId: number,
          date: string,
          clinicId: number,
          clinicName: string,
          clinicAddress: string,
          clinicScore: number,
          price: number,
          typeName: string
        }]) => {
        this.clinics = responseData;
        this.searched = true;
      }, error => {
        this.handleError(error);
      });
    }
  }

  isEmpty(value) {
    if(value === null || value === undefined || value === '')
      return true;
    else
      return false;
  }


  convertScoreToStars(score){
    this.stars = [];
    for(let i=1;i<=score;i++){
      this.stars.push(i);
    }
    return this.stars;
  }
}
