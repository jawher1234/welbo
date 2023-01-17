package tn.esprit.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class MembersOfCompany implements Serializable {
    @Id
    private String nid;
    private String name;
    private String lastName;

    public MembersOfCompany(String nid, String name, String lastName) {
        this.nid = nid;
        this.name = name;
        this.lastName = lastName;
    }

    public MembersOfCompany() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }
}
