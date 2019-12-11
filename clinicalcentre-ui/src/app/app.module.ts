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
    ActivationpageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AngularFontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
