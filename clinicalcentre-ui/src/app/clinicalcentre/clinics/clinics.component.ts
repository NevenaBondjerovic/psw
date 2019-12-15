import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clinics',
  templateUrl: './clinics.component.html',
  styleUrls: ['./clinics.component.css']
})
export class ClinicsComponent implements OnInit {

  clinics: any[];
  selectedClinic: {
    id: number
    name: string,
    address: string,
    score: number
   };

  constructor() {
    this.selectedClinic = null;
    this.clinics = [
      { id: 1, name: "Clinic 1", address: "Some clinic address 1", score: [1,2,3,4,5]},
      { id: 2, name: "Clinic 2", address: "Some clinic address 2", score: [1,2,3]},
      { id: 3, name: "Clinic 3", address: "Some clinic address 3", score: [1,2,3,4]},
      { id: 4, name: "Clinic 4", address: "Some clinic address 4", score: [1]}
    ];
  }

  ngOnInit() {
  }

  onSelect(clinic){
    this.selectedClinic = clinic;
  }

}
