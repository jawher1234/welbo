package tn.esprit.spring.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Rss implements Serializable {
    private String link;
    private String description;
    private Date createdAt;
}
