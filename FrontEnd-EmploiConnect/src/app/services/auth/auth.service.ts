import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { LoginDto } from 'src/app/dtos/requests/LoginDto';
import { RegisterDto } from 'src/app/dtos/requests/RegisterDto';
import { UpdateUserRoleRequestDTO } from 'src/app/dtos/requests/UpdateUserRoleRequestDTO';
import { AuthenticationResponseDTO } from 'src/app/dtos/responses/AuthenticationResponseDTO';
import { CompanyResponseDTO } from 'src/app/dtos/responses/CompanyResponseDTO';
import { RoleResponseDTO } from 'src/app/dtos/responses/RoleResponseDTO';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<AuthenticationResponseDTO[]> {
    return this.http.get<AuthenticationResponseDTO[]>(`${environment.apiUrl}/auth`);
  }

  login(user: LoginDto): Observable<AuthenticationResponseDTO> {
    this.removeToken()
    return this.http.post<AuthenticationResponseDTO>(`${environment.apiUrl}/auth/authenticate`, user)
      .pipe(
        map(response => {
          
          this.setToken(response.token);

          return response;
        })
      );
  }

  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  removeToken(): void {
    localStorage.removeItem('token');
  }


  register(user: RegisterDto): Observable<string>{
    this.removeToken()
    return this.http.post<{ token : string }>(`${environment.apiUrl}/auth/register`, user)
      .pipe(
         map(response => {
           this.setToken(response.token)
           return response.token
         }),
      );
  }

  getCandidateUsers(): Observable<AuthenticationResponseDTO[]> {
    return this.http.get<AuthenticationResponseDTO[]>(`${environment.apiUrl}/auth/candidates`);
  }
  getRecruiterUsers(): Observable<AuthenticationResponseDTO[]> {
    return this.http.get<AuthenticationResponseDTO[]>(`${environment.apiUrl}/auth/recruiters`);
  }
  getAllRoles(): Observable<RoleResponseDTO[]> {
    return this.http.get<RoleResponseDTO[]>(`${environment.apiUrl}/role`);
  }

  updateUserRole(userId: number, request: UpdateUserRoleRequestDTO): Observable<any> {
    return this.http.put<any>(`${environment.apiUrl}/role/${userId}/updateRole`, request);
  }

  getAllCompanies(): Observable<CompanyResponseDTO[]> {
    return this.http.get<CompanyResponseDTO[]>(`${environment.apiUrl}/companies`);
  }
  
  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/auth/${id}`);
  }
 
}
