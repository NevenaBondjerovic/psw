import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router'

@Component({
  selector: 'app-activationpage',
  templateUrl: './activationpage.component.html',
  styleUrls: ['./activationpage.component.css']
})
export class ActivationpageComponent implements OnInit {

  id: string = null;
  usersUrl: string = 'http://localhost:8080/users/'

  constructor(private _Activatedroute:ActivatedRoute,
    private http: HttpClient, private router: Router) {
  }

  ngOnInit() {
   this._Activatedroute.paramMap.subscribe(params => {
       this.id = params.get('id');
       this.http.get(this.usersUrl + this.id )
       .subscribe(() => {}, error => {
          this.router.navigate(['/login']);
       });
   });
  }

}
