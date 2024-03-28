import { Component } from '@angular/core';
import { AuthenticationResponseDTO } from 'src/app/dtos/responses/AuthenticationResponseDTO';
import { AuthService } from 'src/app/services/auth/auth.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-list-recruiters',
  templateUrl: './list-recruiters.component.html',
  styleUrls: ['./list-recruiters.component.css']
})
export class ListRecruitersComponent {
  recruiterUsers: AuthenticationResponseDTO[] = [];

  constructor(private navbarService: NavbarService,private authService: AuthService) { }

  ngOnInit(): void {
    this.navbarService.setShowNavbar(false); // Masquer la barre de navigation
    this.loadRecruiterUsers();
  }
  
  loadRecruiterUsers(): void {
    this.authService.getRecruiterUsers().subscribe(
      users => {
        this.recruiterUsers = users;
        console.log(users);
      },
      error => {
        console.error('Error fetching candidate users:', error);
      }
    );
  }
  deleteUser(id: number): void {
    this.authService.deleteUser(id)
      .subscribe(() => {
        this.recruiterUsers = this.recruiterUsers.filter( user => user.id !== id);
      });
  }
}
