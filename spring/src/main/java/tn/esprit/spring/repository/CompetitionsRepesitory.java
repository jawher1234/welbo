package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Competition;

public interface CompetitionsRepesitory extends CrudRepository<Competition, Long> {

}
