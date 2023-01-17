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

import tn.esprit.spring.entity.Questionnaires;
import tn.esprit.spring.entity.Questions;
import tn.esprit.spring.service.IQuestionnairesService;

@RestController
@RequestMapping("/Questionnaire")
public class QuestionnairesController {
	@Autowired
	IQuestionnairesService QuestionnaireService;
	
	//http://localhost:8083/PIDEV/Questionnaire/retrieve-all-Questionnaires
	@GetMapping("/retrieve-all-Questionnaires")
	public List<Questionnaires> getQuestionnaires() {
	List<Questionnaires> listQuestionnaires = QuestionnaireService.retrieveAllQuestionnaires();
	return listQuestionnaires;
	}
	
	//http://localhost:8083/PIDEV/Questionnaire/add-Questionnaire
	@PostMapping("/add-Questionnaire")
	@ResponseBody
	public Questionnaires addQuestionnaire(@RequestBody Questionnaires c)
	{
		Questionnaires Questionnaire = QuestionnaireService.addQuestionnaire(c);
		return Questionnaire;
	}
	
	//http://localhost:8083/PIDEV/Questionnaire/remove-Questionnaire/{id}
	@DeleteMapping("/remove-Questionnaire/{id}")
	@ResponseBody
	public void removeQuestionnaire(@PathVariable("id") Long id) {
		QuestionnaireService.deleteQuestionnaire(id);
	}
	
	//http://localhost:8083/PIDEV/Questionnaire/modify-Questionnaire
	@PutMapping("/modify-Questionnaire")
	@ResponseBody
	public Questionnaires modifyQuestionnaire(@RequestBody Questionnaires Questionnaire) {
	return QuestionnaireService.updateQuestionnaire(Questionnaire);
	}
	
	//http://localhost:8083/PIDEV/Questionnaire/retrieve-all-questions-for-questionnaire/{id}
		@GetMapping("/retrieve-all-questions-for-questionnaire/{id}")
		public List<Questions> retrieveallQuestionsForQuestionnaire(@PathVariable("id") Long id) {
		List<Questions> listQuestions = QuestionnaireService.retrieveallQuestionsForQuestionnaire(id);
		return listQuestions;
		}
}
