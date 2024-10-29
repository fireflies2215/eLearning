package com.eLearning.controller;

import com.eLearning.dto.ResponseModel;
import com.eLearning.dto.user.FacultyTimetableRequestDto;
import com.eLearning.entity.Result;
import com.eLearning.repository.ResultRepository;
import com.eLearning.service.FacultyService;
import com.eLearning.service.ResultService;
import com.eLearning.service.StudentClassService;
import com.eLearning.service.TimetableService;
import com.eLearning.util.ResourceAlreadyExitsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/faculty")
@CrossOrigin(origins = "*")
public class FacultyController {
    @Autowired
    private StudentClassService studentClassService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private TimetableService timetableService;

    @Autowired
    private ResultService resultService;


    @GetMapping("/list")
    public ResponseEntity<ResponseModel> getFaculties(){
        var responseModel=new ResponseModel();
        try{
            var facultyList=facultyService.listOfFaculty();
            responseModel.setStatus("success");
            responseModel.setMessage("List of Faculties");
            responseModel.setData(facultyList);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{facultyId}")
    public ResponseEntity<ResponseModel> getFaculties(@PathVariable("facultyId") int facultyId){
        var responseModel=new ResponseModel();
        try{
            var faculty=facultyService.getFaculty(facultyId);
            if(faculty.isPresent()){
                responseModel.setStatus("success");
                responseModel.setMessage("Faculty Details");
                responseModel.setData(faculty);
                return new ResponseEntity<>(responseModel, HttpStatus.OK);
            }
            else{
                responseModel.setStatus("failed");
                responseModel.setMessage("Faculty not found");
                return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
            }

        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/delete/{facultyId}")
    public ResponseEntity<ResponseModel> removeFaculty(@PathVariable("facultyId") int facultyId){
        var responseModel=new ResponseModel();
        try{
            facultyService.deleteFaculty(facultyId);

            responseModel.setStatus("success");
            responseModel.setMessage("Faculty deleted");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);

        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/timetable")
    public ResponseEntity<ResponseModel> getTimetableByDateAndFaculty(@RequestBody FacultyTimetableRequestDto facultyTimetableRequestDto){
        var responseModel=new ResponseModel();
        try{
            var timetableList=timetableService.getTimeTableByDateAndFaculty(facultyTimetableRequestDto.getDate(),facultyTimetableRequestDto.getFacultyId());

            responseModel.setStatus("success");
            responseModel.setData(timetableList);
            responseModel.setMessage("Faculty Timetable");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);

        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
