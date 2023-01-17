package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Questions;
import tn.esprit.spring.service.IQuestionsService;

@RestController
@RequestMapping("/Question")
public class QuestionsController {
	@Autowired
	IQuestionsService Questionservice;
	
	//http://localhost:8083/PIDEV/Question/retrieve-all-Questions
	@GetMapping("/retrieve-all-Questions")
	public List<Questions> getQuestions() {
	List<Questions> listQuestions = Questionservice.retrieveAllQuestions();
	return listQuestions;
	}
	
	//http://localhost:8083/PIDEV/Question/add-Question
	@PostMapping("/add-Question")
	@ResponseBody
	public Questions addQuestion(@RequestBody Questions c)
	{
		Questions Question = Questionservice.addQuestion(c);
		return Question;
	}
	
	//http://localhost:8083/PIDEV/Question/remove-Question/{id}
	@DeleteMapping("/remove-Question/{id}")
	@ResponseBody
	public void removeQuestion(@PathVariable("id") Long id) {
		Questionservice.deleteQuestion(id);
	}
	
	//http://localhost:8083/PIDEV/Question/modify-Question
	@PutMapping("/modify-Question")
	@ResponseBody
	public Questions modifyQuestion(@RequestBody Questions Question) {
	return Questionservice.updateQuestion(Question);
	}
}
