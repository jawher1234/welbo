package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Answers;
import tn.esprit.spring.repository.AnswersRepository;

@Service
public class AnswersServiceImp implements IAnswersService{
	@Autowired
	AnswersRepository AnswersRepository;

	@Override
	public List<Answers> retrieveAllAnswers() {
		List<Answers> Answers = (List<Answers>) AnswersRepository.findAll();
		return Answers;
	}

	@Override
	public Answers addAnswer(Answers a) {
		AnswersRepository.save(a);
		 return a;
	}

	@Override
	public void deleteAnswer(Long id) {
		AnswersRepository.delete(AnswersRepository.findById(id).get());
	}

}
