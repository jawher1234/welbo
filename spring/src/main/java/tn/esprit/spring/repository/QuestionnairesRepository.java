package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Questionnaires;
import tn.esprit.spring.entity.Questions;

@Repository
public interface QuestionnairesRepository extends CrudRepository <Questionnaires,Long>{
	@Query(value = "SELECT * FROM questions WHERE questionaire_id=:id",nativeQuery = true)
	List<Questions> retrieveallQuestionsForQuestionnaire(@Param("id") Long id);
}