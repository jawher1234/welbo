package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Competition;
import tn.esprit.spring.service.CompetitionService;

@RestController
public class CompetitionController {
	@Autowired
	CompetitionService competitionService;
	
	@GetMapping("/competitions")
	@ResponseBody
	public List<Competition> getAllCompetitions(){
		List<Competition> list = competitionService.getAllCompetitions();
		return list ;
		
		
	}
	
	
	@PostMapping("/AjouterCompetition")
	@ResponseBody
	public Competition AjouterCompetition(@RequestBody Competition c) {
		return competitionService.AjouterCompetition(c);
	}
	
	//   @ResponseBody
	 //   public void UpdateEvent(@RequestBody Event e ) {
		//	eventService.UpdateEvent(e);
		
	//	}
	@PutMapping("/modify-Competition")
	@ResponseBody
	public Competition modifyCompetition(@RequestBody Competition c) {
	return competitionService.updateCompetition(c);
	}
	
	@DeleteMapping("deleteCompetition/{id}")
	@ResponseBody
	public void deleteEvent(@PathVariable("id") Long idCompetition) {
		competitionService.deleteCompetitionById(idCompetition);
		
		
		
	}
	

}
