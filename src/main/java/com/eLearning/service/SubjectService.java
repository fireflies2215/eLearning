package com.eLearning.service;

import com.eLearning.entity.Subject;
import com.eLearning.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    public Subject addSubject(Subject subject);
    public List<Subject> getSubjects();

    public Optional<Subject> getsubjectBySubjectId(int subjectId);
    public List<Subject> getSubjectByFaculty(int facultyId);
    public List<Subject> getSubjectByClass(int classId);
}
