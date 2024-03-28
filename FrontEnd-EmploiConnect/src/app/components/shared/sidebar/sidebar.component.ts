import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  @ViewChild('toggleBtn', { static: true }) toggleBtn!: ElementRef;

  constructor(private router:Router) { }

  ngOnInit(): void {
    this.toggleBtn.nativeElement.addEventListener('click', () => {
      const sidebar = document.querySelector("#sidebar");
      if (sidebar !== null) {
        sidebar.classList.toggle("expand");
      }
    });
  }

  
  Logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('firstName');
    localStorage.removeItem('familyName');
    this.router.navigate(["login"])
  }
}
