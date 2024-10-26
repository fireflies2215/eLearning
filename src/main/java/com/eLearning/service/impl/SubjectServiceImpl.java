package com.eLearning.service.impl;

import com.eLearning.entity.Subject;
import com.eLearning.repository.SubjectRepository;
import com.eLearning.service.SubjectService;
import com.eLearning.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject addSubject(Subject subject) {
        try{
            var registeredSubject=subjectRepository.save(subject);
            return registeredSubject;
        }
        catch (DataIntegrityViolationException ex){

            throw new DataIntegrityViolationException(subject.getSubjectCode()+" subject code already registered");
        }
        catch (Exception e){

            throw new RuntimeException("Error While Registering subject : "+e.getMessage());
        }
    }

    @Override
    public List<Subject> getSubjects() {
        try{
            var subjectList=subjectRepository.findAll();
            return subjectList;
        }

        catch (Exception e){
            throw new RuntimeException("Error while fetching subjects : "+e.getMessage());
        }
    }

    @Override
    public Optional<Subject> getsubjectBySubjectId(int subjectId) {
        try {
            var subject=subjectRepository.findById(subjectId);
//            if(subject.isEmpty()){
//                throw new ResourceNotFoundException("subject is found for "+subjectId+" id");
//            }
            return subject;

        }
        catch (Exception e){
            throw new RuntimeException("Error While fetching subjects");
        }
    }

    @Override
    public List<Subject> getSubjectByFaculty(int facultyId) {
        try{
            var subjects=subjectRepository.findByUserId(facultyId);
            return subjects;
        }

        catch (Exception e){
            throw new RuntimeException("Error While fetching subjects");
        }
    }

    @Override
    public List<Subject> getSubjectByClass(int classId) {
        try{
            var subjects=subjectRepository.findByStudentClassId(classId);
            return subjects;
        }

        catch (Exception e){
            throw new RuntimeException("Error While fetching subjects");
        }
    }
}
