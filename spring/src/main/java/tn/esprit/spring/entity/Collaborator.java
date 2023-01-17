package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Collaborator implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String company;
    private String Address;
    private LocalDateTime createdAt;
    @JsonIgnore
    @OneToMany(mappedBy="collaborator", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<CollaboratorContact> collaboratorContacts = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy="collaborator", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Offer> offer = new ArrayList<>();

}
