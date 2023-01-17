package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Questions;

public interface IQuestionsService {
	List<Questions> retrieveAllQuestions();
	Questions  addQuestion(Questions q);
	void deleteQuestion(Long id);
	Questions updateQuestion(Questions q);
}
