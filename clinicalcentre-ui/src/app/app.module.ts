import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RegistrationComponent } from './registration/registration.component';
import { ClinicalcentreComponent } from './clinicalcentre/clinicalcentre.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { HomeComponent } from './clinicalcentre/home/home.component';
import { ClinicsComponent } from './clinicalcentre/clinics/clinics.component';
import { MyprofileComponent } from './clinicalcentre/myprofile/myprofile.component';
import { RequestsComponent } from './clinicalcentre/requests/requests.component';
import { ActivationpageComponent } from './activationpage/activationpage.component';
import { ProfiledataComponent } from './clinicalcentre/myprofile/profiledata/profiledata.component';
import { GlobalVariablesService } from './global-variables.service';
import { HistoryComponent } from './clinicalcentre/myprofile/history/history.component';
import { MedicalrecordComponent } from './clinicalcentre/myprofile/medicalrecord/medicalrecord.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    ClinicalcentreComponent,
    HomeComponent,
    ClinicsComponent,
    MyprofileComponent,
    RequestsComponent,
    ActivationpageComponent,
    ProfiledataComponent,
    HistoryComponent,
    MedicalrecordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AngularFontAwesomeModule
  ],
  providers: [GlobalVariablesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
