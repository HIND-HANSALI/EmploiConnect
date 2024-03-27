import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CompanyRequestDTO } from 'src/app/dtos/requests/CompanyRequestDTO';
import { CompanyResponseDTO } from 'src/app/dtos/responses/CompanyResponseDTO';
import { CompanyService } from 'src/app/services/company/company.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit{
  companyForm: FormGroup;
  isEditMode: boolean = false;
  companyToUpdate: CompanyResponseDTO | null = null;

  constructor(private navbarService: NavbarService,private fb: FormBuilder, private companyService: CompanyService, private router: Router, private route: ActivatedRoute) {
    this.companyForm = this.fb.group({
      name: ['', Validators.required],
      sector: ['', Validators.required],
      location: ['', Validators.required],
      foundationDate: ['', Validators.required],
      specializations: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.navbarService.setShowNavbar(false);
    const companyId = this.route.snapshot.paramMap.get('id');
    if (companyId) {
      this.isEditMode = true;
      this.companyService.getCompanyById(+companyId).subscribe(company => {
        this.companyToUpdate = company;
        this.companyForm.patchValue({
          name: company.name,
          sector: company.sector,
          location: company.location,
          foundationDate: company.foundationDate,
          specializations: company.specializations,
          description: company.description
        });
      });
    } else {
      this.isEditMode = false;
      this.companyForm.reset();
    }
  }

  get formTitle(): string {
    return this.isEditMode ? 'Update Company' : 'Create Company';
  }

  get submitButtonText(): string {
    return this.isEditMode ? 'Update Company' : 'Save Company';
  }

  onSubmit() {
    const companyData: CompanyRequestDTO = this.companyForm.value;
    if (this.isEditMode) {
      this.updateCompany(companyData);
    } else {
      this.saveCompany(companyData);
    }
  }

  private updateCompany(companyData: CompanyRequestDTO) {
    const companyId = this.route.snapshot.paramMap.get('id');
    if (companyId && this.companyToUpdate) {
      this.companyService.updateCompany(+companyId, companyData).subscribe(updatedCompany => {
        console.log('Company updated successfully:', updatedCompany);
        this.router.navigate(['/dashboard/companies']);
      });
    }
  }

  private saveCompany(companyData: CompanyRequestDTO) {
    this.companyService.createCompany(companyData).subscribe(newCompany => {
      console.log('Company created successfully:', newCompany);
      this.companyForm.reset();
      this.router.navigate(['/dashboard/companies']);
    });
  }
}
