import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {

  readonly API_URL='http://localhost:8083/PIDEV/Questionnaire';

  constructor(private httpClient: HttpClient) { }

  RetrieveAllQuestionnaires(){
    return this.httpClient.get(`${this.API_URL}/retrieve-all-Questionnaires`);
  }
  
/*addProduct (product:any){
  return this.httpClient.post(S{this. API_URL}/add-product`, product)
editProduct(product:any){
  return this.httpCclient.put(${this.API_URL}/edit-product, product)
deleteProduct(idProduct:any){
  return this.httpClient.delete(S{this.API_URL}/delete-product/${idProduct})*/

}
