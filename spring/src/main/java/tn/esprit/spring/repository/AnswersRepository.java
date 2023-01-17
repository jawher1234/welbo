package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Answers;

@Repository
public interface AnswersRepository extends CrudRepository <Answers,Long>{

}
