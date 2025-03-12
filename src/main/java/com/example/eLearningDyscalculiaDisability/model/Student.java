package com.example.eLearningDyscalculiaDisability.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String email;
    private String gradeLevel;

    // Constructors
    public Student() {}

    public Student(String username, String password, String email, String gradeLevel) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gradeLevel = gradeLevel;
    }
}
