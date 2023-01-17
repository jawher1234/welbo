package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Votes;
import tn.esprit.spring.service.VotesServiceImpl;

import java.util.List;
@RestController
@RequestMapping("/votes")
public class VoteRestController {
@Autowired
VotesServiceImpl votesServiceImpl;

@PostMapping("/add-Vote")
@ResponseBody
public Votes addVote(@RequestBody Votes v)
{
Votes votes = votesServiceImpl.addvote(v);
return votes;
}

@DeleteMapping("/remove-Votes/{id}")
@ResponseBody
public void removeVotes(@PathVariable("id")Integer Id)
{
votesServiceImpl.deleteVotes(Id);
}

@GetMapping("/retrieve-All-votes")
@ResponseBody
public List<Votes> getVotes() {
	List<Votes> listVotes = votesServiceImpl.retrieveAllVotes();
	return listVotes;
	}
}

