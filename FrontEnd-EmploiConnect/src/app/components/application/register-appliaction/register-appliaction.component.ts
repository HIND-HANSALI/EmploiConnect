import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ApplicationRequestDto } from 'src/app/dtos/requests/ApplicationRequestDTO';
import { ApplicationService } from 'src/app/services/application/application.service';

@Component({
  selector: 'app-register-appliaction',
  templateUrl: './register-appliaction.component.html',
  styleUrls: ['./register-appliaction.component.css']
})
export class RegisterAppliactionComponent implements OnInit {
  applicationForm: FormGroup;
  offerId: number=0;

  errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private applicationService: ApplicationService
  ) {
    this.applicationForm = this.formBuilder.group({
      title: ['', Validators.required],
      profile: ['', Validators.required],
      cv: [null, Validators.required]
    });
  }
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.offerId = params['id'];
      // Now you can use the offerId to save the application
    });
  }

  onSubmit(): void {
    
    if (this.applicationForm.invalid) {
      return;
    }

    const formData: ApplicationRequestDto = {
      title: this.applicationForm.get('title')?.value,
      profile: this.applicationForm.get('profile')?.value || '',
      cv: this.applicationForm.get('cv')?.value || ''
    };
console.log(formData);
    this.applicationService.saveApplication(formData,this.offerId).subscribe(
      (response) => {
        console.log('Application saved successfully:', response);
        
        this.applicationForm.reset();
      },
      (error) => {
        console.error('Error saving application:', error);
        this.errorMessage = error.error.message;
      }
    );
  }
}
