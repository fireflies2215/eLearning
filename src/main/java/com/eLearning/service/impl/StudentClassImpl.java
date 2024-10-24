package com.eLearning.service.impl;

import com.eLearning.entity.StudentClass;
import com.eLearning.repository.StudentClassRepository;
import com.eLearning.service.StudentClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
 public class StudentClassImpl implements StudentClassService {



    @Autowired
    private StudentClassRepository studentClassRepository;


    @Override
    public StudentClass addClass(StudentClass studentClass)  {

        try{
            var registeredClass=studentClassRepository.save(studentClass);
            return registeredClass;
        }
        catch (DataIntegrityViolationException ex){
            System.out.println("catch 1 : "+ex.getMessage());
            log.info(ex.getMessage());
            throw new DataIntegrityViolationException(studentClass.getClassName()+" Class already registered");
        }
        catch (Exception e){
            log.info(e.getMessage());
            System.out.println("catch 2 : "+e.getMessage());
            throw new RuntimeException("Error While Registering class : "+e.getMessage());
        }
    }

    @Override
    public List<StudentClass> getAllClass() throws Exception {

        try{
            var classList=studentClassRepository.findAll();
            return classList;
        }

        catch (Exception e){
            throw new Exception("Error while fetching classes : "+e.getMessage());
        }

    }

    @Override
    public StudentClass updateClass(StudentClass studentClass) throws Exception {
        try{
            var updatedClass=studentClassRepository.save(studentClass);
            return updatedClass;
        }

        catch (Exception e){
            throw new Exception("Error While updating class");
        }
    }

    @Override
    public Optional<StudentClass> getClass(int classId) {
        try{
            var studentClass=studentClassRepository.findById(classId);
            return studentClass;
        }

        catch (Exception e){
            throw new RuntimeException("Error while fetching class : "+e.getMessage());
        }
    }

    @Override
    public void deleteClass(int studentClassId) {
        try{
            studentClassRepository.deleteById(studentClassId);
        }

        catch (Exception e){
            throw new RuntimeException("Error While deleting class");
        }
    }
}
