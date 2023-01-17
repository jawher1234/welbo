package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class Departement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Size(max = 50)
    private String name;

    private String description;


    public Departement(Long id, String name, String description) {
        Id = id;
        this.name = name;
        this.description = description;
    }

    public Departement() {
    }

    public Long getId() {return Id;}

    public void setId(Long id) {Id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}


}
