package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@SuppressWarnings("serial")

@Table( name = "Questionnaires")
public class Questionnaires implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; // Clé primaire
	private String name;
	private String description;
	private boolean questionsType;
	/*true=
	 1 Strongly Agree
	2 Agree
	3 Disagree
	4 Strongly Disagree
	false=
	1 Very true
	2 Somewhat true
	3 Not too true
	4 Not at all true*/
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public boolean isquestionsType() {return questionsType;}
	public void setType(boolean questionsType) {this.questionsType = questionsType;}
}