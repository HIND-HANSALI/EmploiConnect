import { CanActivateFn, Router } from '@angular/router';

export const candidateGuard: CanActivateFn = (route, state) => {
  const router = new Router;
  
  const storedRole = localStorage.getItem('role');

  if (storedRole === 'CANDIDATE') {
     return true; 
  } else {
    return false;
  }
};
