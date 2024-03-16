import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {
  private showNavbarSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(true);
  showNavbar$: Observable<boolean> = this.showNavbarSubject.asObservable();

  constructor() { }

  setShowNavbar(value: boolean) {
    this.showNavbarSubject.next(value);
  }
}
