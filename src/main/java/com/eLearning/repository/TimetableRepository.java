package com.eLearning.repository;

import com.eLearning.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable,Integer> {
    List<Timetable> findByDateAndStudentClassId(LocalDate date, int classId);
    List<Timetable> findByDateAndSubject_User_Id(LocalDate date, int facultyId);
}
