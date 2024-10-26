package com.eLearning.controller;


import com.eLearning.dto.ResponseModel;
import com.eLearning.entity.Subject;
import com.eLearning.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/subject")
@CrossOrigin(origins = "*")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/add")
    public ResponseEntity<ResponseModel> createSubject(@RequestBody Subject subject){
        var responseModel = new ResponseModel();

        System.out.println(subject);
        try{
            var createdSubject=subjectService.addSubject(subject);
            responseModel.setStatus("success");
            responseModel.setData(createdSubject);
            responseModel.setMessage("Subject created successfully");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
        catch (DataIntegrityViolationException ex){
            responseModel.setStatus("failed");
            responseModel.setMessage(ex.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/list")
    public ResponseEntity<ResponseModel> getSubjects(){
        var responseModel=new ResponseModel();
        try{
            var subjects=subjectService.getSubjects();
            responseModel.setStatus("success");
            responseModel.setMessage("List of subjects");
            responseModel.setData(subjects);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/by-id")
    public ResponseEntity<ResponseModel> getSubject(@RequestParam(required = true) int subjectId) {
        var responseModel = new ResponseModel();
        try {
            var subject = subjectService.getsubjectBySubjectId(subjectId);
            if (subject.isPresent()) {
                responseModel.setStatus("success");
                responseModel.setMessage("Subject Details");
                responseModel.setData(subject);
                return new ResponseEntity<>(responseModel, HttpStatus.OK);
            } else {
                responseModel.setStatus("failed");
                responseModel.setMessage("Subject not found");
                return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/by-faculty")
    public ResponseEntity<ResponseModel> getSubjectByFaculty(@RequestParam(required = true) int facultyId) {
        var responseModel = new ResponseModel();
        try {
            var subject = subjectService.getSubjectByFaculty(facultyId);

                responseModel.setStatus("success");
                responseModel.setMessage("Subject Details");
                responseModel.setData(subject);
                return new ResponseEntity<>(responseModel, HttpStatus.OK);


        }
        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("by-class")
    public ResponseEntity<ResponseModel> getSubjectByClass(@RequestParam(required = true) int classId) {
        var responseModel = new ResponseModel();
        try {
            var subject = subjectService.getSubjectByClass(classId);

                responseModel.setStatus("success");
                responseModel.setMessage("Subject Details");
                responseModel.setData(subject);
                return new ResponseEntity<>(responseModel, HttpStatus.OK);

        }
        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
