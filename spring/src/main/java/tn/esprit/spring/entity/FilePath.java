package tn.esprit.spring.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilePath implements Serializable {
    private String path;
    private String filename;
}
