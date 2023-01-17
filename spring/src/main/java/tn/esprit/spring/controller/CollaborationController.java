package tn.esprit.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.entity.Collaborator;
import tn.esprit.spring.service.*;
import tn.esprit.spring.service.ICollaboratorService;

import java.util.List;

@RestController
    @RequestMapping(path ="/api/collaborations")
public class CollaborationController {
    @Autowired
    ICollaboratorService iCollaboratorService;
    @Autowired
    ICollaboratorContactService iCollaboratorContactService;
    @Autowired
    IOfferService iOfferService;
    @Autowired
    IOfferReservationService iOfferReservationService;

    //CRUD Collaborator
    @GetMapping
    @RequestMapping(path ="/collaborators")
    public List<Collaborator> getCollaborators(){
        return iCollaboratorService.getCollaborators();
    }

    @GetMapping({"/collaborator/{id}"})
    public Collaborator getCollaborator(@PathVariable Long id){
        return iCollaboratorService.getCollaborator(id);
    }

    @PostMapping
    @RequestMapping(path = "/collaborator/new")
    @ResponseBody
    public Collaborator addCollaborator(@RequestBody Collaborator collaborator){
        return iCollaboratorService.ajouterCollaborator(collaborator);
    }

    @PutMapping
    @RequestMapping(path = "/collaborator/edit")
    @ResponseBody
    public Collaborator editCollaborator(@RequestBody Collaborator collaborator){
        return iCollaboratorService.modifierCollaborator(collaborator);
    }

    @DeleteMapping({"/collaborator/delete/{id}"})
    public void deleteCollaborator(@PathVariable Long id){
        iCollaboratorService.deleteCollaborator(id);
    }




    //CRUD Collaborator Contact
    @GetMapping
    @RequestMapping(path ="/collaborator/allcontacts")
    public List<CollaboratorContact> getCollaboratorContacts(){
        return iCollaboratorContactService.getCollaboratorContacts();
    }
    @GetMapping({"/collaborator/contact/{id}"})
    public CollaboratorContact getCollaboratorContact(@PathVariable Long id){
        return iCollaboratorContactService.getCollaboratorContact(id);
    }

    @PutMapping
    @RequestMapping(path = "/collaborator/contact/edit")
    @ResponseBody
    public CollaboratorContact edit(@RequestBody CollaboratorContact collaboratorContact){
        return iCollaboratorContactService.modifierCollaboratorContact(collaboratorContact);
    }

    @DeleteMapping({"/collaborator/contact/delete/{id}"})
    public void delete(@PathVariable Long id){
        iCollaboratorContactService.deleteCollaboratorContact(id);
    }
    //Add contact info to a collaborator
    @PostMapping("/collaborator/{collaboratorId}/contact/new")
    public ResponseEntity<CollaboratorContact> addcontactToCollaborator(@PathVariable(value = "collaboratorId") Long collaboratorId, @RequestBody CollaboratorContact contactRequest) {
        return   iCollaboratorContactService.addContactToCollaborator(collaboratorId,  contactRequest);
    }
    //Collaborator contact by collaborator
    @GetMapping("/collaborator/{collaboratorId}/contact/")
    public ResponseEntity<List<CollaboratorContact>> contactByCollaborator(@PathVariable(value = "collaboratorId") Long collaboratorId) {
        return   iCollaboratorContactService.getContactByCollaborator(collaboratorId);
    }



    //CRUD Offers

    @GetMapping
    @RequestMapping(path ="/offers")
    public ResponseEntity<Object> getOffers(){
        return iOfferService.getOffers();
    }
    @GetMapping({"/offers/state/{state}"})
    public ResponseEntity<Object> getOffersByState(@PathVariable Boolean state){
        return iOfferService.getOffersByState(state);
    }

    @GetMapping({"/offer/{id}"})
    public ResponseEntity<Object> getOffer(@PathVariable Long id){
        return iOfferService.getOffer(id);
    }

    @PostMapping
    @RequestMapping(path = "/offer/new")
    @ResponseBody
    public ResponseEntity<Object> addOffer(@RequestBody Offer offer){
        return iOfferService.ajouterOffer(offer);
    }

    @PutMapping
    @RequestMapping(path = "/offer/edit")
    @ResponseBody
    public ResponseEntity<Object> editOffer(@RequestBody Offer offer){
        return iOfferService.modifierOffer(offer);
    }
    @PutMapping
    @RequestMapping(path = "/offer/update/state/{id}")
    @ResponseBody
    public ResponseEntity<Object> editOfferState(@PathVariable Long id){
        return iOfferService.updateOfferState(id);
    }
    @DeleteMapping({"/offer/delete/{id}"})
    public ResponseEntity<Object> deleteOffer(@PathVariable Long id){
        return iOfferService.deleteOffer(id);
    }
// reservation management

    @PostMapping
    @RequestMapping(path = "/reserve/{offerId}/{userId}")
    @ResponseBody
    public ResponseEntity<Object> addReservation(@PathVariable(value = "offerId") Long offerId, @PathVariable(value = "userId") Long userId){
        return iOfferReservationService.addReservation(userId,offerId);
    }
    @PostMapping
    @RequestMapping(path = "/remove/reservation/{id}")
    @ResponseBody
    public ResponseEntity<Object> removeReservation(@PathVariable(value = "id") Long id){
        return iOfferReservationService.removeReservation(id);
    }
    @PostMapping
    @RequestMapping(path = "/validate/reservation/{id}")
    @ResponseBody
    public ResponseEntity<Object> validateReservation(@PathVariable(value = "id") Long id){
        return iOfferReservationService.validateReservation(id);
    }
    @GetMapping({"/reservations"})
    public ResponseEntity<Object> getReservations(){
        return iOfferReservationService.findAllReservations();
    }

    @GetMapping({"/reservations/user/{userId}"})
    public ResponseEntity<Object> getReservationsByUser(@PathVariable(value = "userId") Long id){
        return iOfferReservationService.findReservationsByUser(id);
    }
    @GetMapping({"/reservations/offer/{offerId}"})
    public ResponseEntity<Object> getReservationsByOffer(@PathVariable(value = "offerId") Long id){
        return iOfferReservationService.findReservationsByOffer(id);
    }
    @GetMapping({"/reservation/{id}"})
    public ResponseEntity<Object> getReservationsById(@PathVariable(value = "id") Long id){
        return iOfferReservationService.findReservationsById(id);
    }

    @PostMapping({"/offers/to/excel"})
    public void getExcel(@RequestBody FilePath path){
        iOfferService.createExcel(path.getPath()+path.getFilename()+".xlsx");
    }
    @PostMapping({"/offers/from/excel"})
    public void getDataFromExcel(@RequestBody FilePath path){
        iOfferService.dataFromExcel(path.getPath());
    }

}
