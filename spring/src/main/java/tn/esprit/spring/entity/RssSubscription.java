package tn.esprit.spring.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class RssSubscription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime createdAt;

    @ManyToOne
    private User subscriber;
    @ManyToOne
    private RssFeedProvider provider;
}
