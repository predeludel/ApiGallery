package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public Base(LocalDateTime createDateTime, LocalDateTime updateDateTime) {
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }

    public Base() {
    }
}
