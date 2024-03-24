import { Component } from '@angular/core';
import { ApplicationResponseDTO } from 'src/app/dtos/responses/ApplicationResponseDTO';
import { ApplicationService } from 'src/app/services/application/application.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-list-applications',
  templateUrl: './list-applications.component.html',
  styleUrls: ['./list-applications.component.css']
})
export class ListApplicationsComponent {
  applications: ApplicationResponseDTO[] = [];
  constructor(private navbarService: NavbarService,private applicationService: ApplicationService) { }

  ngOnInit(): void {
    this.navbarService.setShowNavbar(false); // Masquer la barre de navigation
    this.loadApplications();
  }
  
  loadApplications(): void {
    this.applicationService.getAllApplications().subscribe(
      applications => {
        this.applications = applications;
        console.log(applications);
      },
      error => {
        console.error('Error fetching applications:', error);
      }
    );
  }
  approveApplication(id: number): void {
    this.applicationService.approveApplication(id).subscribe(
      (response) => {
        console.log('Application approved successfully:', response);
        this.loadApplications();
      },
      (error) => {
        console.error('Error approving application:', error);
      }
    );
  }

  rejectApplication(id: number): void {
    this.applicationService.rejectApplication(id).subscribe(
      (response) => {
        console.log('Application rejected successfully:', response);
        this.loadApplications();
      },
      (error) => {
        console.error('Error rejecting application:', error);
      }
    );
  }
}
