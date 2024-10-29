package com.eLearning.controller;

import com.eLearning.dto.ResponseModel;
import com.eLearning.dto.user.FacultyTimetableRequestDto;
import com.eLearning.entity.Result;
import com.eLearning.service.*;
import com.eLearning.util.ResourceAlreadyExitsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/student")
@CrossOrigin(origins = "*")
public class StudentController {


    @Autowired
    private StudentService studentService;

    @Autowired
    private ResultService resultService;


    @GetMapping("/list")
    public ResponseEntity<ResponseModel> getStudents(){
        var responseModel=new ResponseModel();
        try{
            var studentList=studentService.getStudents();
            responseModel.setStatus("success");
            responseModel.setMessage("List of Students");
            responseModel.setData(studentList);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{studentId}")
    public ResponseEntity<ResponseModel> getStudent(@PathVariable("studentId") int studentId){
        var responseModel=new ResponseModel();
        try{
            var student=studentService.getStudent(studentId);
            if(student.isPresent()){
                responseModel.setStatus("success");
                responseModel.setMessage("student Details");
                responseModel.setData(student);
                return new ResponseEntity<>(responseModel, HttpStatus.OK);
            }
            else{
                responseModel.setStatus("failed");
                responseModel.setMessage("student not found");
                return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
            }

        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/by-class/{classId}")
    public ResponseEntity<ResponseModel> getStudentByClass(@PathVariable("classId") int classId){
        var responseModel=new ResponseModel();
        try{
            var studentList=studentService.getStudentByClass(classId);
            responseModel.setStatus("success");
            responseModel.setMessage("List of Students");
            responseModel.setData(studentList);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/result/add")
    public ResponseEntity<ResponseModel> addStudentResult(@RequestBody Result result){
        var responseModel=new ResponseModel();
        try{
            var studentResult=resultService.addResult(result);
            responseModel.setStatus("success");
            responseModel.setData(studentResult);
            responseModel.setMessage("Result");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);

        }

        catch (ResourceAlreadyExitsException rae){
            responseModel.setStatus("failed");
            responseModel.setMessage(rae.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/result/{studentId}")
    public ResponseEntity<ResponseModel> getStudentResult(@PathVariable("studentId") int studentId){
        var responseModel=new ResponseModel();
        try{
            var studentResult=resultService.getResultByStudent(studentId);
            responseModel.setStatus("success");
            responseModel.setData(studentResult);
            responseModel.setMessage("Result");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);

        }


        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
