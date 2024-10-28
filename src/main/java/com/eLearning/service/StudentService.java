package com.eLearning.service;

import com.eLearning.entity.User;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public List<User> getStudents();
    public Optional<User> getStudent(int userId);
    public List<User> getStudentByClass(int classId);
}
