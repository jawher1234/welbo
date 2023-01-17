package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToOne
    private User commentedby;

    @ManyToOne
    private NewsfeedPost newsfeedPost;

    @ManyToOne
    private Comment comment;

    @JsonIgnore
    @OneToMany(mappedBy="comment", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();

    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
