package tn.esprit.spring.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity

@SuppressWarnings("serial")

@Table( name = "ForumPosts")
public class ForumPosts implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id; // Clé primaire
	
	private Long UserId;
	private String Description;
	
	@Lob
	private byte[] Attachement;
	
	private Long CategorieId;
	private Timestamp UploadTime;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Long getUserId() {return UserId;}
	public void setUserId(Long userId) {UserId = userId;}
	public String getDescription() {return Description;}
	public void setDescription(String description) {Description = description;}
	public byte[] getAttachement() {return Attachement;}
	public void setAttachement(byte[] attachement) {Attachement = attachement;}
	public Timestamp getUploadTime() {return UploadTime;}
	public void setUploadTime(Timestamp uploadTime) {UploadTime = uploadTime;}
	public Long getCategorieId() {return CategorieId;}
	public void setCategorieId(Long categorieId) {CategorieId = categorieId;}
}