import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CompanyRequestDTO } from 'src/app/dtos/requests/CompanyRequestDTO';
import { CompanyResponseDTO } from 'src/app/dtos/responses/CompanyResponseDTO';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) { }

  createCompany(company: CompanyRequestDTO): Observable<CompanyRequestDTO> {
    return this.http.post<CompanyRequestDTO>(`${environment.apiUrl}/companies`, company);
  }

  getAllCompanies(): Observable<CompanyResponseDTO[]> {
    return this.http.get<CompanyResponseDTO[]>(`${environment.apiUrl}/companies`);
  }

  getCompanyById(id: number): Observable<CompanyResponseDTO> {
    return this.http.get<CompanyResponseDTO>(`${environment.apiUrl}/companies/${id}`);
  }

  updateCompany(id: number, company: CompanyRequestDTO): Observable<CompanyRequestDTO> {
    return this.http.put<CompanyRequestDTO>(`${environment.apiUrl}/companies/${id}`, company);
  }

  deleteCompany(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/companies/${id}`);
  }
}
