package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Opinions;

public interface IOpinionsService {
	List<Opinions> retrieveAllOpinions();
	Opinions addOpinion(Opinions a);
	void deleteOpinion(Long id);
	Opinions updateOpinion(Opinions n);
}