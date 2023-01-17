package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Answers;
import tn.esprit.spring.service.IAnswersService;

@RestController
@RequestMapping("/Answers")
public class AnswersController {
	@Autowired
	IAnswersService Answersservice;
	
	//http://localhost:8083/PIDEV/Answers/retrieve-all-Answers
	@GetMapping("/retrieve-all-Answers")
	public List<Answers> getAnswers() {
	List<Answers> listAnswers = Answersservice.retrieveAllAnswers();
	return listAnswers;
	}
	
	//http://localhost:8083/PIDEV/Answers/add-Answer
	@PostMapping("/add-Answer")
	@ResponseBody
	public Answers addAnswer(@RequestBody Answers c)
	{
		Answers Answer = Answersservice.addAnswer(c);
		return Answer;
	}
	
	//http://localhost:8083/PIDEV/Answers/remove-Answer/{id}
	@DeleteMapping("/remove-Answer/{id}")
	@ResponseBody
	public void removeAnswer(@PathVariable("id") Long id) {
		Answersservice.deleteAnswer(id);
	}
}
