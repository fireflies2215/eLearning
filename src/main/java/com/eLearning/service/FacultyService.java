package com.eLearning.service;

import com.eLearning.entity.User;

import java.util.List;
import java.util.Optional;

public interface FacultyService {

    public Optional<User> getFaculty(int facultyId);
    public List<User> listOfFaculty();
    public void deleteFaculty(int facultyId);

}
