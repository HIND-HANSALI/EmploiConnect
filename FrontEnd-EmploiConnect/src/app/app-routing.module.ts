import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { ListOffersComponent } from './components/offer/list-offers/list-offers.component';
import { ListApplicationsComponent } from './components/application/list-applications/list-applications.component';
import { RegisterAppliactionComponent } from './components/application/register-appliaction/register-appliaction.component';
import { SidebarComponent } from './components/shared/sidebar/sidebar.component';
import { ListCandidatsComponent } from './components/candidat/list-candidats/list-candidats.component';
import { ListOffersAdminComponent } from './components/offer/list-offers-admin/list-offers-admin.component';
import { CreateOfferComponent } from './components/offer/create-offer/create-offer.component';

const routes: Routes = [
  {path:'dashboard/applications',component:ListApplicationsComponent},

  {path: '',component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'offers',component: ListOffersComponent},
  {path:'sidebar',component:SidebarComponent},

  {path:'dashboard/candidats',component:ListCandidatsComponent},
  {path:'dashboard/offers',component:ListOffersAdminComponent},
  {path:'dashboard/create-offer',component:CreateOfferComponent},
  { path: 'dashboard/edit-offer/:id', component: CreateOfferComponent },

  
  {path:'applications/register/:id',component:RegisterAppliactionComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
