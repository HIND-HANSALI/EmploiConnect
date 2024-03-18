import { Component, OnInit } from '@angular/core';
import { OfferResponseDTO } from 'src/app/dtos/responses/OfferResponseDTO';
import { OfferService } from 'src/app/services/offer/offer.service';

@Component({
  selector: 'app-list-offers',
  templateUrl: './list-offers.component.html',
  styleUrls: ['./list-offers.component.css']
})
export class ListOffersComponent implements OnInit {
  offers: OfferResponseDTO[]= [];

  constructor(private offerService: OfferService) { }

  ngOnInit(): void {
    this.loadOffers();
  }

  loadOffers(): void {
    this.offerService.getAllOffers().subscribe((offers: OfferResponseDTO[]) => {
        this.offers = offers;
        console.log(offers);
      },
      (error) => {
        console.error('Error fetching offers:', error);
      }
    );
  }
}
