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
import { HistoryComponent } from './clinicalcentre/myprofile/history/history.component';
import { MedicalrecordComponent } from './clinicalcentre/myprofile/medicalrecord/medicalrecord.component';
import { AppointmentsComponent } from './clinicalcentre/appointments/appointments.component';
import { WorkcalendarComponent } from './clinicalcentre/workcalendar/workcalendar.component';
import { SearchComponent } from './clinicalcentre/clinics/search/search.component';
import { DoctorsComponent } from './clinicalcentre/clinics/doctors/doctors.component';
import { ScheduleComponent } from './clinicalcentre/clinics/schedule/schedule.component';
import { AppointmentrequestsComponent } from './clinicalcentre/appointmentrequests/appointmentrequests.component';
import { AppointmentconfirmationComponent } from './appointmentconfirmation/appointmentconfirmation.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'activation', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'activation/:id', component: ActivationpageComponent},
  { path: 'confirmation/:id', component: AppointmentconfirmationComponent},
  { path: 'clinicalcentre', redirectTo: '/clinicalcentre/home', pathMatch: 'full' },
  { path: 'clinicalcentre', component: ClinicalcentreComponent, children: [
    { path: 'home', component: HomeComponent },
    { path: 'searchclinics', component: SearchComponent },
    { path: 'doctors/:clinicId/:date/:type', component: DoctorsComponent },
    { path: 'schedule/:appointmentId', component: ScheduleComponent },
    { path: 'clinics', component: ClinicsComponent },
    { path: 'requests', component: RequestsComponent },
    { path: 'appointmentrequests', component: AppointmentrequestsComponent },
    { path: 'appointments', component: AppointmentsComponent},
    { path: 'workcalendar', component: WorkcalendarComponent},
    { path: 'myprofile', component: MyprofileComponent, children: [
      {path: 'profile', component: ProfiledataComponent},
      {path: 'history', component: HistoryComponent},
      {path: 'medicalrecord', component: MedicalrecordComponent}
    ] }
   ] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
