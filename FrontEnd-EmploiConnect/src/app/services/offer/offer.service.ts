import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OfferRequestDTO } from 'src/app/dtos/requests/OfferRequestDTO';
import { OfferResponseDTO } from 'src/app/dtos/responses/OfferResponseDTO';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  constructor(private http: HttpClient) {}

  getAllOffers(): Observable<OfferResponseDTO[]> {

    return this.http.get<OfferResponseDTO[]>(`${environment.apiUrl}/offers`);
  }
  
  saveOffer(offer: OfferRequestDTO): Observable<OfferRequestDTO> {
    return this.http.post<OfferRequestDTO>(`${environment.apiUrl}/offers`, offer);
  }

  updateOffer(offerId: number, offer: OfferRequestDTO): Observable<OfferRequestDTO> {
    return this.http.put<OfferRequestDTO>(`${environment.apiUrl}/offers/${offerId}`, offer);
  }
  getOfferById(offerId: number): Observable<OfferResponseDTO> {
    return this.http.get<OfferResponseDTO>(`${environment.apiUrl}/offers/${offerId}`);
  }
 

}
