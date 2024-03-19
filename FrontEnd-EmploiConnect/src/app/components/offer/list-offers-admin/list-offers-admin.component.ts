import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OfferResponseDTO } from 'src/app/dtos/responses/OfferResponseDTO';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { OfferService } from 'src/app/services/offer/offer.service';

@Component({
  selector: 'app-list-offers-admin',
  templateUrl: './list-offers-admin.component.html',
  styleUrls: ['./list-offers-admin.component.css']
})
export class ListOffersAdminComponent {
  offers: OfferResponseDTO[]= [];
  constructor(private navbarService: NavbarService,private offerService: OfferService,private router: Router) { }

  ngOnInit(): void {
    this.navbarService.setShowNavbar(false); // Masquer la barre de navigation
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
  deleteOffer(offerId: number): void {
    if (confirm('Are you sure you want to delete this offer?')) {
      this.offerService.deleteOffer(offerId).subscribe(
        () => {
          console.log('Offer with code ${id} deleted successfully');
          // this.router.navigate(['/dashboard/offers']);
          this.loadOffers();
        },
        error => {
          console.error('Error deleting offer:', error);
          
        }
      );
    }
  }
}
