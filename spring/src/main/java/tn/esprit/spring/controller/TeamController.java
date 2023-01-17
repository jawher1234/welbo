package tn.esprit.spring.controller;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Team;
import tn.esprit.spring.entity.TeamPage;
import tn.esprit.spring.entity.TeamSearchCriteria;
import tn.esprit.spring.service.TeameService;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeameService teameService;

    public TeamController(TeameService teameService) {
        this.teameService = teameService;
    }

    @GetMapping
    public ResponseEntity<Page<Team>> getTeamss(TeamPage teamPage,
                                                       TeamSearchCriteria teamSearchCriteria){
        return new ResponseEntity<>(teameService.getteamss(teamPage, teamSearchCriteria),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Team> addEmployee(@RequestBody Team team){
        return new ResponseEntity<>(teameService.addteamss(team), HttpStatus.OK);
    }
    
	
	
	
	//   @ResponseBody
	 //   public void UpdateEvent(@RequestBody Event e ) {
		//	eventService.UpdateEvent(e);
		
	//	}
	@PutMapping("/modify-Team")
	@ResponseBody
	public Team modifyTeam(@RequestBody Team t) {
	return teameService.updateTeam(t);
	}
	
	@DeleteMapping("deleteTeam/{id}")
	@ResponseBody
	public void deleteTeam(@PathVariable("id") Long idTeam) {
		teameService.deleteTeamById(idTeam);
	}
	}	
    
   
