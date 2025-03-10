package com.example.eLearningDyscalculiaDisability.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity @Table(name = "admin")
public class Admin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String permission;

    public Admin() {}

    public Admin(String username, String password, String email, String permission) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.permission = permission;
    }
}
