import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router'

@Component({
  selector: 'app-activationpage',
  templateUrl: './activationpage.component.html',
  styleUrls: ['./activationpage.component.css']
})
export class ActivationpageComponent implements OnInit {

  id: string = null;

  constructor(private _Activatedroute:ActivatedRoute) {
  }

  ngOnInit() {
   this._Activatedroute.paramMap.subscribe(params => {
       this.id = params.get('id');
   });
  }

}
