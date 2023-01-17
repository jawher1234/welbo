package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Competition;
import tn.esprit.spring.repository.CompetitionsRepesitory;
@Service
public class CompetitionServiceImpl implements CompetitionService {
	@Autowired
	CompetitionsRepesitory competitionRepository;
	@Override
	public Competition AjouterCompetition(Competition c) {
		competitionRepository.save(c);
		return c;
	}

	@Override
	public Competition updateCompetition(Competition c) {
		return competitionRepository.save(c);
	}

	@Override
	public void deleteCompetition(Competition c) {
		competitionRepository.delete(c);
		
	}

	@Override
	public void deleteCompetitionById(Long id) {
		competitionRepository.deleteById(id);
		
	}

	@Override
	public Competition getCompetition(Long id) {
		return competitionRepository.findById(id).get();
	}

	@Override
	public List<Competition> getAllCompetitions() {
		return  (List<Competition>) competitionRepository.findAll();

	}

}
