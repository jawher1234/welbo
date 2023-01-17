package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Evaluation;
@Repository
public interface EvaluationRepository extends CrudRepository<Evaluation, Integer> {

}
