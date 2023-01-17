package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class RssFeedProvider implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String link;
    private String image;
    @JsonIgnore
    @OneToMany(mappedBy="provider", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<RssSubscription> subscriptions = new ArrayList<>();

}
