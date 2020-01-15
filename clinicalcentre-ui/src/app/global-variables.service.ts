import { Injectable } from '@angular/core';
import { User } from 'src/app/clinicalcentre/user';

@Injectable({
  providedIn: 'root'
})
export class GlobalVariablesService {

  loggedInUser: User = null;

  constructor() { }
}
