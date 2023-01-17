package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@SuppressWarnings("serial")

@Table( name = "Opinion")
public class Opinions implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; // Clé primaire
	private Long opinionUserId;//eli 3mal review
	private Long reviewedUserId;//eli sarlou review
	private Boolean positive;
	private String description;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getOpinionUserId() {return opinionUserId;}
	public void setOpinionUserId(Long opinionUserId) {this.opinionUserId = opinionUserId;}
	public Long getReviewedUserId() {return reviewedUserId;}
	public void setReviewedUserId(Long reviewedUserId) {this.reviewedUserId = reviewedUserId;}
	public Boolean getPositive() {return positive;}
	public void setPositive(Boolean positive) {this.positive = positive;}
	public boolean isPositive() {return positive;}
	public void setPositive(boolean positive) {this.positive = positive;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
}
