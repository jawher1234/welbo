package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Questions;
import tn.esprit.spring.repository.QuestionsRepository;

@Service
public class QuestionsServiceImp implements IQuestionsService{
	@Autowired
	QuestionsRepository QuestionsRepository;

	@Override
	public List<Questions> retrieveAllQuestions() {
		List<Questions> Questions = (List<Questions>) QuestionsRepository.findAll();
		return Questions;
	}

	@Override
	public Questions addQuestion(Questions q) {
		QuestionsRepository.save(q);
		 return q;
	}

	@Override
	public void deleteQuestion(Long id) {
		QuestionsRepository.delete(QuestionsRepository.findById(id).get());
	}

	@Override
	public Questions updateQuestion(Questions q) {
		return QuestionsRepository.save(q);
	}

}
