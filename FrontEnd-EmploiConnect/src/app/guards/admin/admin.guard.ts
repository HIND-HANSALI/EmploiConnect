import { CanActivateFn, Router } from '@angular/router';

export const adminGuard: CanActivateFn = (route, state) => {
  const router = new Router;
  
  const storedRole = localStorage.getItem('role');

  if (storedRole === 'SUPER_ADMIN') {
     return true; 
  } else {
    return false;
  }
}