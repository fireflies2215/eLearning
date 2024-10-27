package com.eLearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "timetable")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Reference to Subject entity
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // Reference to StudentClass entity
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private StudentClass studentClass;

    // Date of the session
    @Column(name = "date", nullable = false)
    private LocalDate date;

    // Start time of the session
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    // End time of the session
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;



}