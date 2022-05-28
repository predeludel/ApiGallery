package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "base_id", referencedColumnName = "id")
    private Base base;

}
