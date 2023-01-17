package tn.esprit.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.MailRequest;
import tn.esprit.spring.entity.MailResponse;
import tn.esprit.spring.service.EmailService;

@SpringBootApplication
@RestController
public class SpringBootEmailFreemarkerApplication {

	@Autowired
	private EmailService service;

	@PostMapping("/sendingEmail")
	public MailResponse sendEmail(@RequestBody MailRequest request) {
		Map<String, Object> model = new HashMap<>();
		model.put("Name", request.getName());
		model.put("location", "Tunis,Tunisia");
		return service.sendEmail(request, model);

	}


}
