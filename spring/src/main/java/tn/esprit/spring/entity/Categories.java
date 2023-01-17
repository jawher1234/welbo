package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@SuppressWarnings("serial")

@Table( name = "Categories")
public class Categories implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; // Clé primaire
	private String CategName;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getCategName() {return CategName;}
	public void setCategName(String categName) {CategName = categName;}
}