package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@SuppressWarnings("serial")

@Table( name = "Answers")
public class Answers implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; // Clé primaire
	
	private Long QuestionId;
	private Long UserId;
	private int answer;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public long getQuestionId() {return QuestionId;}
	public void setQuestionId(long questionId) {QuestionId = questionId;}
	public Long getUserId() {return UserId;}
	public void setUserId(Long userId) {UserId = userId;}
	public int getAnswer() {return answer;}
	public void setAnswer(int answer) {this.answer = answer;}
}