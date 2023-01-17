package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Questionnaires;
import tn.esprit.spring.entity.Questions;

public interface IQuestionnairesService {
	List<Questionnaires> retrieveAllQuestionnaires();
	Questionnaires  addQuestionnaire(Questionnaires q);
	void deleteQuestionnaire(Long id);
	Questionnaires updateQuestionnaire(Questionnaires q);
	List<Questions> retrieveallQuestionsForQuestionnaire(Long id);
}
