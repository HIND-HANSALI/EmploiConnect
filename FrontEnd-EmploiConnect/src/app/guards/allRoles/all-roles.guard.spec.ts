import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { allRolesGuard } from './all-roles.guard';

describe('allRolesGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => allRolesGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
