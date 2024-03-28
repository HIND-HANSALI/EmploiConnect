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
  totalApplications: number = 0;
  rejectedCount: number = 0;
  approvedCount: number = 0;


  ngOnInit(): void {
    this.navbarService.setShowNavbar(false); // Masquer la barre de navigation
    this.loadApplications();
  }
  
  loadApplications(): void {
    this.applicationService.getAllApplications().subscribe(
      applications => {
        this.applications = applications;
        console.log(applications);
        this.totalApplications = applications.length;
        this.rejectedCount = this.applications.filter(app => app.status === 'REJECTED').length;
        this.approvedCount = this.applications.filter(app => app.status === 'APPROVED').length;
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
  hasAuthorityManager(): boolean {

    const role = localStorage.getItem('role');

    return this.isLoggedIn() && (role === 'SUPER_ADMIN');
  }
  isLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }
  // calculateCounts(): void {
  //   this.rejectedApplicationsCount = this.applications.filter(app => app.status === 'REJECTED').length;
  //   this.approvedApplicationsCount = this.applications.filter(app => app.status === 'APPROVED').length;
  // }
}
