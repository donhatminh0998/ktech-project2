package com.example.Project2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String type;

    @OneToMany(mappedBy = "boardType")
    List<Article> articles;

    public Board(String type) {
        this.type = type;
    }

    public Board() {}

}
