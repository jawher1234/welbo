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
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String description;
    private Boolean state;
    private String imageUrl;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime startsAt;
    private LocalDateTime expiresAt;
    @ManyToOne
    private Collaborator collaborator;
    @JsonIgnore
    @OneToMany(mappedBy="offer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OfferReservation> reservations = new ArrayList<>();

}
