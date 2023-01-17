package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Payload.request.EvalRequest;
import tn.esprit.spring.entity.Evaluation;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.EvaluationRepository;
import tn.esprit.spring.repository.IUserRepository;
import tn.esprit.spring.service.EvaluationServiceImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;
@RestController
@RequestMapping("/evaluation")
public class EvaluationRestController {
	@Autowired
	EvaluationRepository evaluationRepository;
	@Autowired
	EvaluationServiceImpl evaluationServiceImpl;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String email;


	@PostMapping("/add-evaluation")
	@ResponseBody
	public Evaluation addEvaluation(@RequestBody EvalRequest e, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
	Evaluation evaluation = new Evaluation();


		Timestamp now = new Timestamp(System.currentTimeMillis());
		evaluation.setEvaldate(now);
		evaluation.setEvalUser(userRepository.findById(e.getEvalUser()).get());
		evaluation.setClasses(e.getClasses());
		evaluation.setDescription(e.getDescription());
		evaluationRepository.save(evaluation);
		sendEvalEmail(evaluation, getSiteURL(request));
	return evaluation;
	}
	@GetMapping("/retrieve-All-Evaluations")
	@ResponseBody
	public List<Evaluation> getEvaluations()
	{
		List<Evaluation> listEvaluations = evaluationServiceImpl.retrieveAllEvaluation();
		return listEvaluations;
	}
	@GetMapping("/retrieve-EvaluationsByid/{id}")
	@ResponseBody
	public Evaluation getEvaluationById(@PathVariable("id") Integer id)
	{
		Evaluation Evaluation = evaluationServiceImpl.retrieveEvaluation(id);
		return Evaluation;
	}

	@DeleteMapping("/remove-Evaluation/{id}")
	@ResponseBody
	public void removeEvaluation(@PathVariable("id")Integer Id)
	{
	evaluationServiceImpl.deleteEvaluation(Id);
	}
	public void sendEvalEmail(Evaluation evaluation, String siteURL)
			throws MessagingException, UnsupportedEncodingException {
		User user = evaluation.getEvalUser();
		String toAddress = user.getEmail();
		String fromAddress = email;
		String senderName = "WelboTn";
		String subject = "Evaluation";
		String content = "Dear [[name]],<br>"
				+ "You got evaluated:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">CHECK EVALUATION</a></h3>"
				+ "Thank you,<br>"
				+ "WelboTn.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		content = content.replace("[[name]]", user.getUserName());
		String evalURL = siteURL + "/retrieve-EvaluationsByid/:id=" + evaluation.getId();

		content = content.replace("[[URL]]", evalURL);

		helper.setText(content, true);

		mailSender.send(message);

	}
	public String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "/evaluation");
	}
	//@Controller
	//public class DateController {
	//@GetMapping(path = "/date")
	 // public String afficherDate(Model model) {
	   // model.addAttribute("now", new Date());
	    //return "affichageDate";
	 // }
	//}
	
}

	


