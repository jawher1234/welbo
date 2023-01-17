import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
    
}
export const ROUTES: RouteInfo[] = [
    { path: '/icons', title: 'Icons',  icon:'ni-planet text-blue', class: '' },
    { path: '/tables', title: 'Tables',  icon:'ni-bullet-list-67 text-red', class: '' },
    { path: '/forum', title: 'Forum',  icon:'ni ni-collection text-info', class: '' },
    { path: '/categories', title: 'Categories',  icon:'ni-archive-2 text-success', class: '' },
    { path: '/questionnaires', title: 'Questionnaires',  icon:'ni ni-ruler-pencil text-orange', class: '' }
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];
  public isCollapsed = true;

  constructor(private router: Router) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
   });
  }
}
