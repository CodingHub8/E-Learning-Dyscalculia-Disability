package com.example.eLearningDyscalculiaDisability.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity @Table(name = "student")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "grade_level")
    private String grade_level;

    // Constructors
    public Student() {}

    public Student(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.grade_level = null;
    }
}
