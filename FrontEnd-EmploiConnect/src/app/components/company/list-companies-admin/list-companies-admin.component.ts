import { Component, OnInit } from '@angular/core';
import { CompanyResponseDTO } from 'src/app/dtos/responses/CompanyResponseDTO';
import { CompanyService } from 'src/app/services/company/company.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';

@Component({
  selector: 'app-list-companies-admin',
  templateUrl: './list-companies-admin.component.html',
  styleUrls: ['./list-companies-admin.component.css']
})
export class ListCompaniesAdminComponent implements OnInit{
  companies: CompanyResponseDTO[]= [];

  constructor(private navbarService: NavbarService,private companyService: CompanyService) { }

  ngOnInit(): void {
    this.navbarService.setShowNavbar(false);
    this.loadCompanies();
  }

  loadCompanies(): void {
    this.companyService.getAllCompanies()
      .subscribe(companies => this.companies = companies);
  }
  deleteCompany(id: number): void {
    this.companyService.deleteCompany(id)
      .subscribe(() => {
        this.companies = this.companies.filter(company => company.id !== id);
      });
  }
}
