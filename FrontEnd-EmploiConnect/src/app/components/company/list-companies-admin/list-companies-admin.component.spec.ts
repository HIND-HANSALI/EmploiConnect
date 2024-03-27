import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCompaniesAdminComponent } from './list-companies-admin.component';

describe('ListCompaniesAdminComponent', () => {
  let component: ListCompaniesAdminComponent;
  let fixture: ComponentFixture<ListCompaniesAdminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListCompaniesAdminComponent]
    });
    fixture = TestBed.createComponent(ListCompaniesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
