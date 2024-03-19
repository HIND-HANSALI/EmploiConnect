import { Component } from '@angular/core';
import { NavbarService } from './services/navbar/navbar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'FrontEnd-EmploiConnect';
  showNavbar: boolean = true;

  constructor(private navbarService: NavbarService) {}

  ngOnInit() {
    this.navbarService.showNavbar$.subscribe(show => {
      this.showNavbar = show;
    });
  }
}
