import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Questionnaire } from './questionnaire';
import { QuestionnaireService } from './questionnaire.service';

@Component({
  selector: 'app-questionnaires',
  templateUrl: './questionnaires.component.html',
  styleUrls: ['./questionnaires.component.scss']
})
export class QuestionnairesComponent implements OnInit {

  listQuestionnaires : any; 
  form : boolean = false;
  questionnaire!: Questionnaire;
  closeResult! : string;

  constructor(private QuestionnaireService : QuestionnaireService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.RetrieveAllQuestionnaires();;

    this.questionnaire = {
      id: null,
      name: null,
      description: null,
      questionsType: null
    }
  }

  RetrieveAllQuestionnaires(){
    this.QuestionnaireService.RetrieveAllQuestionnaires().subscribe(res => this.listQuestionnaires = res)
  }

  /*
  addProduct(p: any){
    this.productService.addProduct(p).subscribe(() => {
      this.getAllProducts();
      this.form = false;
    });
  }

  editProduct(product : Product){
    this.productService.editProduct(product).subscribe();
  }
  
  deleteProduct(idProduct : any){
    this.productService.deleteProduct(idProduct).subscribe(() => this.getAllProducts())
  }
  */

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
