import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ClinicalcentreComponent } from './clinicalcentre/clinicalcentre.component';
import { HomeComponent } from './clinicalcentre/home/home.component';
import { ClinicsComponent } from './clinicalcentre/clinics/clinics.component';
import { MyprofileComponent } from './clinicalcentre/myprofile/myprofile.component';
import { RequestsComponent } from './clinicalcentre/requests/requests.component';
import { ActivationpageComponent } from './activationpage/activationpage.component';
import { ProfiledataComponent } from './clinicalcentre/myprofile/profiledata/profiledata.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'activation', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'activation/:id', component: ActivationpageComponent},
  { path: 'clinicalcentre', component: ClinicalcentreComponent, children: [
    { path: 'home', component: HomeComponent },
    { path: 'clinics', component: ClinicsComponent },
    { path: 'requests', component: RequestsComponent },
    { path: 'myprofile', component: MyprofileComponent, children: [
      {path: 'profile', component: ProfiledataComponent}
    ] }
   ] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
