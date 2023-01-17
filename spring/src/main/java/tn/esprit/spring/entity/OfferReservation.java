package tn.esprit.spring.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class OfferReservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime reservedAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    User reservedBy;
    @ManyToOne
    Offer offer;
    Boolean state;

}
