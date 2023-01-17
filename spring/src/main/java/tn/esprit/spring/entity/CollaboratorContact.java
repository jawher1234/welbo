package tn.esprit.spring.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
public class CollaboratorContact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String fullname;
    private String role;
    private String email;
    private int phone;
    @ManyToOne
    private Collaborator collaborator;
}
