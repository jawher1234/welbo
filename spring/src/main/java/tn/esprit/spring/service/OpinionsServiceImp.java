package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entity.Opinions;
import tn.esprit.spring.repository.OpinionsRepository;

public class OpinionsServiceImp implements IOpinionsService{
	@Autowired
	OpinionsRepository OpinionRepository;

	@Override
	public List<Opinions> retrieveAllOpinions() {
		List<Opinions> Opinion = (List<Opinions>) OpinionRepository.findAll();
		return Opinion;
	}

	@Override
	public Opinions addOpinion(Opinions a) {
		OpinionRepository.save(a);
		 return a;
	}

	@Override
	public void deleteOpinion(Long id) {
		OpinionRepository.delete(OpinionRepository.findById(id).get());
	}

	@Override
	public Opinions updateOpinion(Opinions n) {
		return OpinionRepository.save(n);
	}
}
