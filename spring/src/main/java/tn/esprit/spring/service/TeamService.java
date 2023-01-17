package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Team;

public interface TeamService {
	Team AjouterTeam(Team t);
	Team updateTeam(Team t);
	 void deleteTeam(Team t);
	 void deleteTeamById(Long id);
	 Team getTeam(Long id);
	 List<Team> getAllTeams();
	

}
