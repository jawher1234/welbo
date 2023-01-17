package tn.esprit.spring.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Tag implements Serializable {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String color;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "postTags")
    private List<NewsfeedPost> posts = new ArrayList<>();

    public Tag() {
    }

    public Tag(Long id, String name, String description, String color, List<NewsfeedPost> posts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.posts = posts;
    }

    public Tag(String name, String description, String color, List<NewsfeedPost> posts) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<NewsfeedPost> getPosts() {
        return posts;
    }

    public void setPosts(List<NewsfeedPost> posts) {
        this.posts = posts;
    }
    public void addPost(NewsfeedPost post){
        posts.add(post);
    }
}
