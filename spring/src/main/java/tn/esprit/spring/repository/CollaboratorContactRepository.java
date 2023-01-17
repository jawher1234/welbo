package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Collaborator;
import tn.esprit.spring.entity.CollaboratorContact;

import java.util.List;
@Repository
public interface CollaboratorContactRepository extends CrudRepository<CollaboratorContact,Long> {
    List<CollaboratorContact> findByCollaborator(Collaborator collaborator);
}
