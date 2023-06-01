import { Component } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front_KonexPruebaTecnica';
  activeItem!: MenuItem;
  items!: MenuItem[];

  ngOnInit() {
    this.items = [
        { label: 'Home', icon: 'pi pi-fw pi-home' , routerLink: 'Medicament'},
        { label: 'Ventas', icon: 'pi pi-fw pi-calendar', routerLink: 'Sale'},
    ];
    this.activeItem = this.items[0];
  }



}
