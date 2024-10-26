package com.eLearning.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="classes")
@Entity
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="class_name",unique = true)
    private String className;

    @Column(name="status")
    private boolean status;
}
