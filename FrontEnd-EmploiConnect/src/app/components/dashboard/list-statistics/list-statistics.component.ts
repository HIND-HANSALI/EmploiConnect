import { Component } from '@angular/core';
import { ApplicationResponseDTO } from 'src/app/dtos/responses/ApplicationResponseDTO';
import { AuthenticationResponseDTO } from 'src/app/dtos/responses/AuthenticationResponseDTO';
import { OfferResponseDTO } from 'src/app/dtos/responses/OfferResponseDTO';
import { ApplicationService } from 'src/app/services/application/application.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { OfferService } from 'src/app/services/offer/offer.service';

@Component({
  selector: 'app-list-statistics',
  templateUrl: './list-statistics.component.html',
  styleUrls: ['./list-statistics.component.css']
})
export class ListStatisticsComponent {
  applications: ApplicationResponseDTO[] = [];
  offers: OfferResponseDTO[]= [];
  candidateUsers: AuthenticationResponseDTO[] = [];

  totalApplications: number = 0;
  totalOffers: number = 0;
  totalCandidates: number = 0;

  constructor(private navbarService: NavbarService,private applicationService: ApplicationService,private offerService:OfferService,private authService: AuthService) { }
  
  

  ngOnInit(): void {
    this.navbarService.setShowNavbar(false); // Masquer la barre de navigation
    this.loadApplications();
    this.loadOffers();
    this.loadCandidateUsers();
  }
  loadApplications(): void {
    this.applicationService.getAllApplications().subscribe(
      applications => {
        this.applications = applications;
        this.totalApplications = applications.length;
        
      },
      error => {
        console.error('Error fetching applications:', error);
      }
    );
  }

  loadOffers(): void {
    this.offerService.getAllOffers().subscribe((offers: OfferResponseDTO[]) => {
        this.offers = offers;
        this.totalOffers=this.offers.length;
      },
      (error) => {
        console.error('Error fetching offers:', error);
      }
    );
  }
  loadCandidateUsers(): void {
    this.authService.getCandidateUsers().subscribe(
      users => {
        this.candidateUsers = users;
        this.totalCandidates=this.candidateUsers.length;
      },
      error => {
        console.error('Error fetching candidate users:', error);
      }
    );
  }
}
