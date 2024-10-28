package com.eLearning.service.impl;

import com.eLearning.entity.User;
import com.eLearning.repository.UserRepository;
import com.eLearning.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getStudents() {
        try{
            var studentList=userRepository.findByRole("student");
            return studentList;
        }

        catch (Exception e){
            throw new RuntimeException("Error while fetching student : "+e.getMessage());
        }
    }

    @Override
    public Optional<User> getStudent(int userId) {
        try{
            var student=userRepository.findByIdAndRole(userId,"student");
            return student;
        }

        catch (Exception e){
            throw new RuntimeException("Error while fetching student : "+e.getMessage());
        }
    }

    @Override
    public List<User> getStudentByClass(int classId) {
        try{
            var studentList=userRepository.findByStudentClassId(classId);
            return studentList;
        }

        catch (Exception e){
            throw new RuntimeException("Error while fetching student : "+e.getMessage());
        }
    }
}
