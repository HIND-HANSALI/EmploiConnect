import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterAppliactionComponent } from './register-appliaction.component';

describe('RegisterAppliactionComponent', () => {
  let component: RegisterAppliactionComponent;
  let fixture: ComponentFixture<RegisterAppliactionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegisterAppliactionComponent]
    });
    fixture = TestBed.createComponent(RegisterAppliactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
