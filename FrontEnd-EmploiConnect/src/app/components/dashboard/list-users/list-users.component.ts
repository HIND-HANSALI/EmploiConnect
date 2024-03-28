import { Component } from '@angular/core';
import { UpdateUserRoleRequestDTO } from 'src/app/dtos/requests/UpdateUserRoleRequestDTO';
import { AuthenticationResponseDTO } from 'src/app/dtos/responses/AuthenticationResponseDTO';
import { CompanyResponseDTO } from 'src/app/dtos/responses/CompanyResponseDTO';
import { RoleResponseDTO } from 'src/app/dtos/responses/RoleResponseDTO';
import { AuthService } from 'src/app/services/auth/auth.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent {
  users: AuthenticationResponseDTO[] = [];
  selectedUser: AuthenticationResponseDTO | null = null;
  selectedRole: number | null = null;
  roles: RoleResponseDTO[] = []; 
  selectedCompany: number | null = null;
  companies: CompanyResponseDTO[] = [];
 

  constructor(private navbarService: NavbarService,private authService: AuthService) { }

  ngOnInit(): void {
    this.navbarService.setShowNavbar(false); // Masquer la barre de navigation
    this.loadData();
  }
  loadData(): void {
    this.loadUsers();
    this.loadRoles();
    this.loadCompanies();
  }
  loadUsers(): void {
    this.authService.getAllUsers().subscribe(
      users => {
        this.users = users;
        console.log(users);
      },
      error => {
        console.error('Error fetching users:', error);
      }
    );
  }
  loadRoles(): void {
    this.authService.getAllRoles()
      .subscribe(roles => this.roles = roles);
  }
  loadCompanies(): void {
    this.authService.getAllCompanies()
      .subscribe(companies => this.companies = companies);
  }

  openModal(user: AuthenticationResponseDTO): void {
    console.log('Selected User:', this.selectedUser);
    console.log('openModal() function called');
    this.selectedUser = user;
    this.selectedCompany = user.company.id;
    this.selectedRole = user.role.id;
    console.log("company ",this.selectedCompany);
    // console.log("hhhhh",this.selectedCompany);
  }

  closeModal(): void {
    this.selectedUser = null;
  }
  updateUserRole(): void {
    // console.log('Selected Company before updating role:', this.selectedCompany);
    if (this.selectedUser && this.selectedRole) {
      const selectedRoleId = Number(this.selectedRole);
      const selectedRole = this.roles.find(role => role.id === selectedRoleId);
      // console.log('Selected Company ID:', this.selectedCompany);
  
      if (selectedRole) {
        const request : UpdateUserRoleRequestDTO= {
          roleId: selectedRoleId,
          companyId: this.selectedCompany !== null ? Number(this.selectedCompany) : null
        };
        console.log('Update Request:', request);
  
        this.authService.updateUserRole(this.selectedUser.id, request)
          .subscribe(() => {
            console.log('Update successful');
            console.log('Update Request updated:', request);
            if (this.selectedUser) {
              this.selectedUser.role = selectedRole;
              this.closeModal();
            } else {
              console.error('Selected user is null');
            }
          });
      } else {
        console.error('Selected role not found');
      }
    }
  
  }
  
  deleteUser(id: number): void {
    this.authService.deleteUser(id)
      .subscribe(() => {
        this.users = this.users.filter(user => user.id !== id);
      });
  }

}
