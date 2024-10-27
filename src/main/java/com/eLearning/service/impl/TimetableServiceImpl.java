package com.eLearning.service.impl;

import com.eLearning.entity.Timetable;
import com.eLearning.repository.TimetableRepository;
import com.eLearning.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService {


    @Autowired
    private TimetableRepository timetableRepository;

    @Override
    public Timetable addTimetable(Timetable timetable) {
        try{
            var registeredTimetable=timetableRepository.save(timetable);
            return registeredTimetable;
        }
//        catch (DataIntegrityViolationException ex){
//
//            throw new DataIntegrityViolationException(timetable.getSubjectCode()+" subject code already registered");
//        }
        catch (Exception e){

            throw new RuntimeException("Error While Registering Timetable : "+e.getMessage());
        }
    }

    @Override
    public List<Timetable> getTimeTableByDateAndClass(LocalDate date, int classId) {

        System.out.println("date : "+date);
        System.out.println("classId : "+classId);
        try{
            var timetableList=timetableRepository.findByDateAndStudentClassId(date,classId);
            return timetableList;
        }

        catch (Exception e){
            throw new RuntimeException("Error while fetching timetable : "+e.getMessage());
        }
    }

    @Override
    public List<Timetable> getTimeTableByDateAndFaculty(LocalDate date, int facultyId) {
        try{
            var timetableList=timetableRepository.findByDateAndSubject_User_Id(date,facultyId);
            return timetableList;
        }

        catch (Exception e){
            throw new RuntimeException("Error while fetching timetable : "+e.getMessage());
        }
    }
}
