package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Collaborator;
import tn.esprit.spring.repository.CollaboratorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CollaboratorService implements ICollaboratorService {
    @Autowired
    CollaboratorRepository collaboratorRepository;
    @Override
    public Collaborator ajouterCollaborator(Collaborator collaborator) {
        collaborator.setCreatedAt(LocalDateTime.now());
        collaboratorRepository.save(collaborator);
        return collaborator;
    }

    @Override
    public void deleteCollaborator(Long collaboratorId) {
        collaboratorRepository.deleteById(collaboratorId);
    }

    @Override
    public List<Collaborator> getCollaborators() {

        return (List<Collaborator>) collaboratorRepository.findAll();
    }

    @Override
    public Collaborator modifierCollaborator( Collaborator collaborator) {
        collaboratorRepository.save(collaborator);
        return collaborator;
    }

    @Override
    public Collaborator getCollaborator(Long collaboratorId) {
        return collaboratorRepository.findById(collaboratorId).get();
    }
}
