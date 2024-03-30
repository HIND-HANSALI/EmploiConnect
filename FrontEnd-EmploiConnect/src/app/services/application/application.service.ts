import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationRequestDto } from 'src/app/dtos/requests/ApplicationRequestDTO';
import { ApplicationResponseDTO } from 'src/app/dtos/responses/ApplicationResponseDTO';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  constructor(private http: HttpClient) { }

  saveApplication(application: ApplicationRequestDto,offerId:number): Observable<ApplicationRequestDto> {
    return this.http.post<ApplicationRequestDto>(`${environment.apiUrl}/applications/${offerId}`,application);
  }
  saveApplicationNew(formData: FormData, offerId: number): Observable<ApplicationRequestDto> {
    return this.http.post<ApplicationRequestDto>(`${environment.apiUrl}/applications/admin/${offerId}`, formData);
  }

  getAllApplications(): Observable<ApplicationResponseDTO[]> {
    return this.http.get<ApplicationResponseDTO[]>(`${environment.apiUrl}/applications`);
  }
  approveApplication(id: number): Observable<ApplicationResponseDTO> {
    return this.http.put<any>(`${environment.apiUrl}/applications/${id}/approve`, {});
  }

  rejectApplication(id: number): Observable<ApplicationResponseDTO> {
    return this.http.put<any>(`${environment.apiUrl}/applications/${id}/reject`, {});
  }
}
