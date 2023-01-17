package tn.esprit.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Votes;
import tn.esprit.spring.repository.VotesRepository;

import java.util.List;
@Service
@Slf4j
public class VotesServices implements VotesServiceImpl {
	@Autowired
	VotesRepository votesRepository;
	@Override
	public List<Votes> retrieveAllVotes() {
		List<Votes>votes=(List<Votes>)votesRepository.findAll();
		for (Votes vote:votes)
		{
						log.info(" vote :" + vote);
		}
    return votes;
	}
	@Override
	public Votes addvote(Votes v) {
		return votesRepository.save(v);
	}

	@Override
	public void deleteVotes(Integer id) {
		votesRepository.deleteById(id);
		
	}

	@Override
	public Votes updateVotes(Votes v) {
		return votesRepository.save(v);
	}

	@Override
	public Votes retrieveVotes(Integer id) {
		Votes vote = votesRepository.findById(id).orElse(null);
		System.out.println("Votes :" + vote);
		return vote;
	}
}
