package tn.esprit.spring.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@SuppressWarnings("serial")

@Table( name = "Questions")
public class Questions implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; // Clé primaire
	private String Content;
	private Long QuestionaireId;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getContent() {return Content;}
	public void setContent(String content) {Content = content;}
	public Long getQuestionaireId() {return QuestionaireId;}
	public void setQuestionaireId(Long questionaireId) {QuestionaireId = questionaireId;}
}