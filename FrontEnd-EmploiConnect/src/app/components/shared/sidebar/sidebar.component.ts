import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  @ViewChild('toggleBtn', { static: true }) toggleBtn!: ElementRef;

  constructor() { }

  ngOnInit(): void {
    this.toggleBtn.nativeElement.addEventListener('click', () => {
      const sidebar = document.querySelector("#sidebar");
      if (sidebar !== null) {
        sidebar.classList.toggle("expand");
      }
    });
  }
}
