import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCandidatsComponent } from './list-candidats.component';

describe('ListCandidatsComponent', () => {
  let component: ListCandidatsComponent;
  let fixture: ComponentFixture<ListCandidatsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListCandidatsComponent]
    });
    fixture = TestBed.createComponent(ListCandidatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
