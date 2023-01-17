package tn.esprit.spring.service;

import tn.esprit.spring.entity.Collaborator;

import java.util.List;

public interface ICollaboratorService {
    public Collaborator ajouterCollaborator(Collaborator collaborator);
    public void deleteCollaborator(Long collaboratorId);
    public List<Collaborator> getCollaborators();
    public Collaborator modifierCollaborator(Collaborator collaborator);
    public Collaborator getCollaborator(Long collaboratorId);
    
}
