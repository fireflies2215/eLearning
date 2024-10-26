package com.eLearning.service.impl;

import com.eLearning.dto.user.UserLoginRequestDto;
import com.eLearning.entity.User;
import com.eLearning.repository.UserRepository;
import com.eLearning.service.FacultyService;
import com.eLearning.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listOfFaculty() {
        try{
            var facultyList=userRepository.findByRole("faculty");
            return facultyList;
        }

        catch (Exception e){
            throw new RuntimeException("Error while fetching faculty : "+e.getMessage());
        }
    }

//    @Override
//    public User updateFacultyDetails(User user) {
//        try{
//            var updatedFaculty=userRepository.save(user);
//            return updatedFaculty;
//        }
//
//        catch (Exception e){
//            throw new RuntimeException("Error While updating faculty details");
//        }
//    }

    @Override
    public Optional<User> getFaculty(int facultyId) {
        try{
            var faculty=userRepository.findById(facultyId);
            return faculty;
        }

        catch (Exception e){
            throw new RuntimeException("Error While fetching faculty");
        }
    }

    @Override
    public void deleteFaculty(int facultyId) {
        try{
           userRepository.deleteById(facultyId);
        }

        catch (Exception e){
            throw new RuntimeException("Error while deleting faculty : "+e.getMessage());
        }
    }
}
