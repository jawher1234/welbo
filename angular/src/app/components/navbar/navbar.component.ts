import { Component, OnInit, ElementRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location, LocationStrategy, PathLocationStrategy } from '@angular/common';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { navbar } from './navbar';
import { navbarService } from './navbar.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  public focus;
  public listTitles: any[];
  public location: Location;

  listNotifications : any;
  form : boolean = false;
  navbar!: navbar;
  closeResult! : string;
  
  constructor(private navbarService : navbarService, private modalService: NgbModal, location: Location,  private element: ElementRef, private router: Router) {
    this.location = location;
  }

  ngOnInit() {
    this.listTitles = ROUTES.filter(listTitle => listTitle);

    this.RetrieveAllNotifications();;

    this.navbar = {
      id: null,
      UserId: null,
      Status: null
    }
  }

  RetrieveAllNotifications(){
    this.navbarService.RetrieveAllNotifications().subscribe(res => this.listNotifications = res)
  }

  getTitle(){
    var titlee = this.location.prepareExternalUrl(this.location.path());
    if(titlee.charAt(0) === '#'){
        titlee = titlee.slice( 1 );
    }

    for(var item = 0; item < this.listTitles.length; item++){
        if(this.listTitles[item].path === titlee){
            return this.listTitles[item].title;
        }
    }
    return 'Dashboard';
  }

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(
      (result) => {}, (reason) => { this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;}
    );
  }

  private getDismissReason(reason: any): string {
    if(reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    }else if(reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    }else{
      return  `with: ${reason}`;
    }
  }

  closeForm(){
  }

  cancel(){
    this.form = false;
  }

}
