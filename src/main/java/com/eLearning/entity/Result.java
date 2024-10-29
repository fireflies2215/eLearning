package com.eLearning.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Many-to-One relationship to User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Many-to-One relationship to Subject
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // Many-to-One relationship to StudentClass
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private StudentClass studentClass;

    @Column(name = "total_marks", nullable = false)
    private int totalMarks;

    @Column(name = "marks_obtained", nullable = false)
    private int marksObtained;


    @Column(name = "feedback")
    private String feedback;


//    @Column(name = "status", nullable = false)
//    private String status;  // e.g., "Pass" or "Fail"

}
