import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OfferRequestDTO } from 'src/app/dtos/requests/OfferRequestDTO';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { OfferService } from 'src/app/services/offer/offer.service';

@Component({
  selector: 'app-create-offer',
  templateUrl: './create-offer.component.html',
  styleUrls: ['./create-offer.component.css']
})
export class CreateOfferComponent  implements OnInit{
  @Input() offerToUpdate: OfferRequestDTO | null = null;
  offerForm: FormGroup;

  isEditMode: boolean = false;

  constructor(private navbarService: NavbarService,private fb: FormBuilder, private offerService: OfferService,private router: Router,private route: ActivatedRoute) {
    this.offerForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      contrat: ['', Validators.required],
    });
  }
 
  ngOnInit(): void {
    this.navbarService.setShowNavbar(false);
    const offerId = this.route.snapshot.paramMap.get('id');
    if (offerId) {
      this.isEditMode = true;
      this.offerService.getOfferById(+offerId).subscribe(offer => {
        this.offerToUpdate = offer;
        console.log(this.offerToUpdate);
        this.offerForm.patchValue({
          title: offer.title,
          description: offer.description,
          contrat: offer.contrat
        });
      });
    }else {
      this.isEditMode = false;
      this.offerForm.reset();
    }
  

  }
  get formTitle(): string {
    return this.isEditMode ? 'Update Offer' : 'Create Offer';
  }

  get submitButtonText(): string {
    return this.isEditMode ? 'Update Offer' : 'Save Offer';
  }


  onSubmit() {
    const offerData: OfferRequestDTO = this.offerForm.value;
    if (this.isEditMode) {
      this.updateOffer(offerData);
    } else {
      this.saveOffer(offerData);
    }
  }
  
  private updateOffer(offerData: OfferRequestDTO) {
    const offerId = this.route.snapshot.paramMap.get('id');
    if (offerId && this.offerToUpdate) {
      this.offerService.updateOffer(+offerId, offerData).subscribe(updatedOffer => {
        console.log('Offer updated successfully:', updatedOffer);
        this.router.navigate(['/dashboard/offers']);
      });
    }
  }
  
  private saveOffer(offerData: OfferRequestDTO) {
    this.offerService.saveOffer(offerData).subscribe(newOffer => {
      console.log('Offer created successfully:', newOffer);
      this.offerForm.reset();
      this.router.navigate(['/dashboard/offers']);
    });
  }
 
     

}
