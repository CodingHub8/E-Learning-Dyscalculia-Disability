package com.example.eLearningDyscalculiaDisability.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "student") // Ensure this matches your database table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String grade_level;

    // Constructors
    public Student() {}

    public Student(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        
    }
}
