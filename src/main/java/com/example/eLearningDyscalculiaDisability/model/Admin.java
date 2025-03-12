package com.example.eLearningDyscalculiaDisability.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
<<<<<<< HEAD
@Entity @Table(name = "admin")
=======

@Entity @Table(name = "admin")

>>>>>>> 57133f82322e04600a9af32f366f5bc5e4e15efd
public class Admin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "permission")
    private String permission;

    public Admin() {}

    public Admin(String username, String password, String email, String permission) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.permission = permission;
    }
}
