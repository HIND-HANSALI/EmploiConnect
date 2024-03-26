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

 

  getAllApplications(): Observable<ApplicationResponseDTO[]> {
    return this.http.get<ApplicationResponseDTO[]>(`${environment.apiUrl}/applications`);
  }
 
}
