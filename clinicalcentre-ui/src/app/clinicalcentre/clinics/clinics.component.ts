import { Component, OnInit } from '@angular/core';
import { Clinic } from 'src/app/clinicalcentre/clinic';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-clinics',
  templateUrl: './clinics.component.html',
  styleUrls: ['./clinics.component.css']
})
export class ClinicsComponent implements OnInit {

  clinics: [Clinic];
  selectedClinic: {
    id: number
    name: string,
    address: string,
    score: number
   };
   clinicsUrl: string = 'http://localhost:8080/clinics';
   typesUrl: string = 'http://localhost:8080/types';
   stars: any[] = [];
   loadingData: boolean = false;
   searchEnabled: boolean = false;
   allTypesOfAppointments: [string];

  errorMessage = null;
  httpStatusInternalServerError = 500;
  serverErrorMessage = 'Error happened while processing the data, please contact the administrator.';
  httpStatusServerNotAvailable = 0;
  serviceNotAvailableErrorMessage = 'The service is not available at the moment. Please try again later.';


  constructor(private http: HttpClient) {
    this.loadingData = true;
    this.selectedClinic = null;
    this.http.get(this.clinicsUrl)
    .subscribe((responseData: [Clinic]) => {
      this.clinics = responseData;
    }, error => {
      this.handleError(error);
    });
    this.http.get(this.typesUrl)
      .subscribe((responseData: [string]) => {
        this.allTypesOfAppointments = responseData;
      }, error => {
        this.handleError(error);
      });
    this.loadingData = false;
  }

  ngOnInit() {
  }

  onSelect(clinic){
    this.loadingData = true;
    this.http.get(this.clinicsUrl + "/" + clinic.id)
    .subscribe((responseData: Clinic) => {
      this.selectedClinic = responseData;
      this.loadingData = false;
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

  convertScoreToStars(score){
    this.stars = [];
    for(let i=1;i<=score;i++){
      this.stars.push(i);
    }
    return this.stars;
  }

  enableDisableSearch(){
    this.searchEnabled = !this.searchEnabled;
  }

}
