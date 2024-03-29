import { CanActivateFn, Router } from '@angular/router';

export const allRolesGuard: CanActivateFn = (route, state) => {
  const router = new Router;

  const role = localStorage.getItem('role');

  if (role === 'SUPER_ADMIN' || role === 'RECRUITER' || role === 'CANDIDATE') {
    return true; 
 } else {
   return false;
 }
};
