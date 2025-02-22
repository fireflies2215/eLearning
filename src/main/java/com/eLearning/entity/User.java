package com.eLearning.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Table(name="users")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username",unique = true)
    private String username;

    @Column(name="email",unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

}
