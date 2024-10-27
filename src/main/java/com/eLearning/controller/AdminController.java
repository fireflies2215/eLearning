package com.eLearning.controller;

import com.eLearning.dto.ResponseModel;
import com.eLearning.dto.user.UserLoginRequestDto;
import com.eLearning.entity.StudentClass;
import com.eLearning.entity.Timetable;
import com.eLearning.service.FacultyService;
import com.eLearning.service.StudentClassService;
import com.eLearning.service.TimetableService;
import com.eLearning.service.UserService;
import com.eLearning.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*")
public class AdminController {


    @Autowired
    private UserService userService;
    @Autowired
    private StudentClassService studentClassService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private TimetableService timetableService;

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> adminLogin(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        var responseModel = new ResponseModel();
        try {
            var loginUser = userService.loginAdminUser(userLoginRequestDto);
            responseModel.setStatus("success");

            responseModel.setData(loginUser);
            responseModel.setMessage("Admin login successfully");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        } catch (ResourceNotFoundException re) {
            responseModel.setStatus("failed");
            responseModel.setMessage(re.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @PostMapping("/class/register")
    public ResponseEntity<ResponseModel> registerClass(@RequestBody StudentClass studentClass){
        var responseModel=new ResponseModel();
        try{
            var registeredClass=studentClassService.addClass(studentClass);
            responseModel.setStatus("success");
            responseModel.setMessage("class successfully registers");
            responseModel.setData(registeredClass);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
        catch (DataIntegrityViolationException dv){
            responseModel.setStatus("failed");
            responseModel.setMessage(dv.getMessage().toString());
            return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage().toString());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/class/list")
    public ResponseEntity<ResponseModel> getAllClass(){
        var responseModel=new ResponseModel();
        try{
            var classList=studentClassService.getAllClass();
            responseModel.setStatus("success");
            responseModel.setMessage("List of classes");
            responseModel.setData(classList);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<ResponseModel> getClass(@PathVariable("classId") int classId){
        var responseModel=new ResponseModel();
        try{
            var studentClass=studentClassService.getClass(classId);
            if(studentClass.isPresent()){
                responseModel.setStatus("success");
                responseModel.setMessage("class Details");
                responseModel.setData(studentClass);
                return new ResponseEntity<>(responseModel, HttpStatus.OK);
            }
            else{
                responseModel.setStatus("failed");
                responseModel.setMessage("class not found");
                return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
            }

        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/class/delete/{classId}")
    public ResponseEntity<ResponseModel> deleteStudentClass(@PathVariable("classId") int classId){
        var responseModel=new ResponseModel();

        try{
            studentClassService.deleteClass(classId);
            responseModel.setStatus("success");
            responseModel.setMessage("class deleted");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/class/update")
    public ResponseEntity<ResponseModel> updateClass(@RequestBody StudentClass studentClass){
        var responseModel=new ResponseModel();
        try{
            var updatedClass=studentClassService.updateClass(studentClass);
            responseModel.setStatus("success");
            responseModel.setMessage("class successfully updated");
            responseModel.setData(updatedClass);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
        
        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/timetable/register")
    public ResponseEntity<ResponseModel> registerTimetable(@RequestBody Timetable timetable){
        var responseModel=new ResponseModel();
        try{
            var registeredTimetable=timetableService.addTimetable(timetable);
            responseModel.setStatus("success");
            responseModel.setMessage("Timetable successfully registers");
            responseModel.setData(registeredTimetable);
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }

        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage().toString());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
