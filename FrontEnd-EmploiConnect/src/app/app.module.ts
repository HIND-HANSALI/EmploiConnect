import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { LoginComponent } from './components/auth/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { SidebarComponent } from './components/shared/sidebar/sidebar.component';
import { CreateOfferComponent } from './components/offer/create-offer/create-offer.component';
import { ListOffersComponent } from './components/offer/list-offers/list-offers.component';
import { ListApplicationsComponent } from './components/application/list-applications/list-applications.component';
import { RegisterAppliactionComponent } from './components/application/register-appliaction/register-appliaction.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { JwtInterceptor } from './interceptors/jwt/jwt.interceptor';
import { ListCandidatsComponent } from './components/candidat/list-candidats/list-candidats.component';
import { ListOffersAdminComponent } from './components/offer/list-offers-admin/list-offers-admin.component';
import { ListRecruitersComponent } from './components/recruiter/list-recruiters/list-recruiters.component';
import { ListStatisticsComponent } from './components/dashboard/list-statistics/list-statistics.component';
import { ListCompaniesAdminComponent } from './components/company/list-companies-admin/list-companies-admin.component';
import { CreateCompanyComponent } from './components/company/create-company/create-company.component';
import { ListUsersComponent } from './components/dashboard/list-users/list-users.component';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { NavbarDashboardComponent } from './components/shared/navbar-dashboard/navbar-dashboard.component';
import { AngularFireStorageModule } from '@angular/fire/compat/storage';
import { AngularFireModule } from '@angular/fire/compat';
import { environment } from 'src/environment/environment';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    NavbarComponent,
    SidebarComponent,
    CreateOfferComponent,
    ListOffersComponent,
    ListApplicationsComponent,
    RegisterAppliactionComponent,
    ListCandidatsComponent,
    ListOffersAdminComponent,
    ListRecruitersComponent,
    ListStatisticsComponent,
    ListCompaniesAdminComponent,
    CreateCompanyComponent,
    ListUsersComponent,
    NavbarDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbDropdownModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireStorageModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
