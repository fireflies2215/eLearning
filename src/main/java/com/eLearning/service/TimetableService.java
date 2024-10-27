package com.eLearning.service;

import com.eLearning.entity.Timetable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface TimetableService {
    public Timetable addTimetable(Timetable timetable);
    public List<Timetable> getTimeTableByDateAndClass(LocalDate date, int classId);
    public List<Timetable> getTimeTableByDateAndFaculty(LocalDate date,int facultyId);

}
