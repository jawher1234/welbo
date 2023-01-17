package tn.esprit.spring.service;

import tn.esprit.spring.entity.*;
import tn.esprit.spring.repository.TeamCriteriaRepository;
import tn.esprit.spring.repository.TeamRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TeameService {

    private final TeamRepository teamRepository;
    private final TeamCriteriaRepository teamCriteriaRepository;

    public TeameService(TeamRepository teamRepository,
                           TeamCriteriaRepository teamCriteriaRepository) {
        this.teamRepository = teamRepository;
        this.teamCriteriaRepository = teamCriteriaRepository;
    }

    public Page<Team> getteamss(TeamPage teamPage,
                                       TeamSearchCriteria teamSearchCriteria){
        return teamCriteriaRepository.findAllWithFilters(teamPage, teamSearchCriteria);
    }

    public Team addteamss(Team team){
        return teamRepository.save(team);
    }
	
	public Team updateTeam(Team t) {
		
		return teamRepository.save(t);
	}

	
	public void deleteTeam(Team t) {
		teamRepository.delete(t);
	}

	
	public void deleteTeamById(Long id) {
		teamRepository.deleteById(id);
		
	}


	public Team getTeam(Long id) {
		
		return teamRepository.findById(id).get();
	}


	public List<Team> getAllTeams() {
		return  (List<Team>) teamRepository.findAll();
	}

}
