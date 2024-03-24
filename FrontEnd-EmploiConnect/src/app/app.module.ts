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
    ListOffersAdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
