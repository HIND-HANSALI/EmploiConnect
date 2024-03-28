import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar-dashboard',
  templateUrl: './navbar-dashboard.component.html',
  styleUrls: ['./navbar-dashboard.component.css']
})
export class NavbarDashboardComponent {
  firstName: string | null = '';
  familyName: string | null = '';
  constructor(private router: Router) {
    this.firstName = localStorage.getItem('firstName');
    this.familyName = localStorage.getItem('familyName');

  }
  hasAuthorityMember(): boolean {

    const role = localStorage.getItem('role');

    return this.isLoggedIn() && (role === 'ROLE_MEMBER');
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('token') !== null;
  }

  isLoggedOut(): boolean{
    return localStorage.getItem('token') == null;
  }

  Logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    this.router.navigate(["login"])
  }
}
