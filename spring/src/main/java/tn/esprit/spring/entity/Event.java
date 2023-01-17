package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "event")
public class Event {

	

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long idEvent ;
	@NotNull(message = "Event name is required.")
	@Size(min = 2, max = 30, message = "Event name must be between 2 and 30 characters.")
	@Column(name = "name", nullable = false)
	private String name ;
	@NotNull(message = "Event description is required.")
	@Size(min = 2, max = 200, message = "Event descripton must be between 2 and 200 characters.")
	@Column(name = "description", nullable = false)
	private String description ;
	@NotNull(message = "Event categorie is required.")
	@Size(min = 2, max = 200, message = "Event categorie must be between 2 and 200 characters.")
	@Column(name = "categorie", nullable = false)
	private String categorie ;
	 
	@Temporal(TemporalType.TIMESTAMP)
	  @Column(name = "time", nullable = false)
	private Date time ;
	
	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
	private byte[] image ;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "events", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List <User> users ;
	


	

}
