package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Table(name = "newsfeedpost")
public class NewsfeedPost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    @ManyToOne
    private User postedby;


    private String image;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "newsfeedpostsTags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List< Tag > postTags = new ArrayList<>();
    private LocalDateTime CreatedAt;
    private LocalDateTime ModifiedAt;

    @JsonIgnore
    @OneToMany(mappedBy="newsfeedPost", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();
    public void addTag(Tag tag){
        postTags.add(tag);
    }


}
