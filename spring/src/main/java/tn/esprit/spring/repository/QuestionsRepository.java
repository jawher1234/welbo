package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Questions;

@Repository
public interface QuestionsRepository extends CrudRepository <Questions,Long>{

}