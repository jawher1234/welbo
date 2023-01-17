package tn.esprit.spring.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Badges implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@NotEmpty(message = "Name may not be empty")
	@Size(min = 3, max = 32, message = "Name must be between 3 and 32 characters long")
	@Column
	@OrderBy(value = "nom asc")
	private String nom;
	@Lob
	@Column
	private byte[] Data;
	@Column
	private String FileName;
	//@Max(value = 150, message = "max value 150") 
	@Column
	private String description;
	@JsonIgnore
	@ManyToOne
	private Votes vote;
	
	public Badges(@Max(value = 150, message = "max value 150") String description, byte[] data,String fileName ) {
		super();
		
		this.FileName = fileName;
		this.description = description;
		this.Data = data;
	}
	
	}
	

