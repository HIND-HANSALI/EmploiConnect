import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRecruitersComponent } from './list-recruiters.component';

describe('ListRecruitersComponent', () => {
  let component: ListRecruitersComponent;
  let fixture: ComponentFixture<ListRecruitersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListRecruitersComponent]
    });
    fixture = TestBed.createComponent(ListRecruitersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
