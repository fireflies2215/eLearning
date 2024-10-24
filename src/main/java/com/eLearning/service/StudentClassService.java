package com.eLearning.service;

import com.eLearning.entity.StudentClass;

import java.util.List;
import java.util.Optional;

public interface StudentClassService {
    public StudentClass addClass(StudentClass studentClass);
    public List<StudentClass> getAllClass() throws Exception;
    public StudentClass updateClass(StudentClass studentClass) throws Exception;

    public Optional<StudentClass> getClass(int classId);
    public void deleteClass(int studentClassId);
}
