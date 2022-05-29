package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User user;
    private String name;
    private String attachFilePath;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "base_id", referencedColumnName = "id")
    private Base base;

}
