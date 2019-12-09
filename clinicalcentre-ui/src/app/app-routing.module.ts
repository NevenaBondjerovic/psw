import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ClinicalcentreComponent } from './clinicalcentre/clinicalcentre.component';
import { HomeComponent } from './clinicalcentre/home/home.component';
import { ClinicsComponent } from './clinicalcentre/clinics/clinics.component';
import { MyprofileComponent } from './clinicalcentre/myprofile/myprofile.component';
import { RequestsComponent } from './clinicalcentre/requests/requests.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'clinicalcentre', component: ClinicalcentreComponent, children: [
    { path: 'home', component: HomeComponent },
    { path: 'clinics', component: ClinicsComponent },
    { path: 'requests', component: RequestsComponent },
    { path: 'myprofile', component: MyprofileComponent }
   ] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
