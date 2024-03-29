import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
    // redirecting users to the login page if no token is found in localStorage
    const router = new Router();

    if(localStorage.getItem('token')){
      return true;
    }else {
      router.navigate(['login'])
      return false;
    }
};
