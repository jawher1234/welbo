package tn.esprit.spring.entity;



import javax.persistence.*;

@Entity
@Table(name = "roles")

public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private ERole name;


	public Role(int id, String role_employee) {

	}

	public Role(Integer id, ERole name) {
		this.id = id;
		this.name = name;
	}

	public Role(ERole name) {
		this.name = name;
	}

	public Role() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}
