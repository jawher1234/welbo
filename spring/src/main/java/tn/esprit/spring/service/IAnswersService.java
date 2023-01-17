package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Answers;

public interface IAnswersService {
	List<Answers> retrieveAllAnswers();
	Answers  addAnswer(Answers a);
	void deleteAnswer(Long id);
}
