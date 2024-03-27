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
import { ListRecruitersComponent } from './components/recruiter/list-recruiters/list-recruiters.component';
import { ListStatisticsComponent } from './components/dashboard/list-statistics/list-statistics.component';
import { ListCompaniesAdminComponent } from './components/company/list-companies-admin/list-companies-admin.component';
import { CreateCompanyComponent } from './components/company/create-company/create-company.component';

const routes: Routes = [
  {path:'dashboard/applications',component:ListApplicationsComponent},

  {path: '',component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'offers',component: ListOffersComponent},
  {path:'sidebar',component:SidebarComponent},
  {path:'dashboard/recruiters',component:ListRecruitersComponent},
  {path:'dashboard/statistics',component:ListStatisticsComponent},
  {path:'dashboard/candidates',component:ListCandidatsComponent},
  {path:'dashboard/offers',component:ListOffersAdminComponent},
  {path:'dashboard/companies',component:ListCompaniesAdminComponent},
  {path:'dashboard/create-company',component:CreateCompanyComponent},
  { path: 'dashboard/edit-company/:id', component: CreateCompanyComponent},
  {path:'dashboard/create-offer',component:CreateOfferComponent},
  { path: 'dashboard/edit-offer/:id', component: CreateOfferComponent },

  
  {path:'applications/register/:id',component:RegisterAppliactionComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
