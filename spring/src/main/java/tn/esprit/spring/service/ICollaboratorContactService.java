package tn.esprit.spring.service;

import org.springframework.http.ResponseEntity;
import tn.esprit.spring.entity.CollaboratorContact;

import java.util.List;

public interface ICollaboratorContactService {
    public CollaboratorContact ajouterCollaboratorContact(CollaboratorContact collaboratorContact);
    public void deleteCollaboratorContact(Long collaboratorContactId);
    public List<CollaboratorContact> getCollaboratorContacts();
    public CollaboratorContact modifierCollaboratorContact(CollaboratorContact collaboratorContact);
    public CollaboratorContact getCollaboratorContact(Long collaboratorContactId);
    public ResponseEntity<CollaboratorContact> addContactToCollaborator(Long collaboratorId, CollaboratorContact collaboratorContact);
    public ResponseEntity<List<CollaboratorContact>> getContactByCollaborator(Long collaboratorId);
}
