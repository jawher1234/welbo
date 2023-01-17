package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Questionnaires;
import tn.esprit.spring.entity.Questions;
import tn.esprit.spring.repository.QuestionnairesRepository;

@Service
public class QuestionnairesServiceImp implements IQuestionnairesService{
	@Autowired
	QuestionnairesRepository QuestionnairesRepository;

	@Override
	public List<Questionnaires> retrieveAllQuestionnaires() {
		List<Questionnaires> Questionnaires = (List<Questionnaires>) QuestionnairesRepository.findAll();
		return Questionnaires;
	}

	@Override
	public Questionnaires addQuestionnaire(Questionnaires q) {
		QuestionnairesRepository.save(q);
		 return q;
	}

	@Override
	public void deleteQuestionnaire(Long id) {
		QuestionnairesRepository.delete(QuestionnairesRepository.findById(id).get());
	}

	@Override
	public Questionnaires updateQuestionnaire(Questionnaires q) {
		return QuestionnairesRepository.save(q);
	}

	@Override
	public List<Questions> retrieveallQuestionsForQuestionnaire(Long id) {
		List<Questions> Questions = (List<Questions>) QuestionnairesRepository.retrieveallQuestionsForQuestionnaire(id);
		return Questions;
	}

}