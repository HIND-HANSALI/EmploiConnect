import { Component } from '@angular/core';
import { AuthenticationResponseDTO } from 'src/app/dtos/responses/AuthenticationResponseDTO';
import { AuthService } from 'src/app/services/auth/auth.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-list-candidats',
  templateUrl: './list-candidats.component.html',
  styleUrls: ['./list-candidats.component.css']
})
export class ListCandidatsComponent {
  candidateUsers: AuthenticationResponseDTO[] = [];
 

  constructor(private navbarService: NavbarService,private authService: AuthService) { }

  ngOnInit(): void {
    this.navbarService.setShowNavbar(false); // Masquer la barre de navigation
    this.loadCandidateUsers();
  }
  loadCandidateUsers(): void {
    this.authService.getCandidateUsers().subscribe(
      users => {
        this.candidateUsers = users;
        console.log(users);
      },
      error => {
        console.error('Error fetching candidate users:', error);
      }
    );
  }

}
