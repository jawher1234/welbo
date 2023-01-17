import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class navbarService {
  readonly API_URL='http://localhost:8083/PIDEV/Notification';

  constructor(private httpClient: HttpClient) { }

  RetrieveAllNotifications(){
    return this.httpClient.get(`${this.API_URL}/retrieve-all-
    RetrieveAllNotifications`);
  }
}
