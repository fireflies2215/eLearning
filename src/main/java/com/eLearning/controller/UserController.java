package com.eLearning.controller;

import com.eLearning.dto.ResponseModel;
import com.eLearning.dto.user.UserDto;
import com.eLearning.dto.user.UserLoginRequestDto;
import com.eLearning.service.UserService;
import com.eLearning.util.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ResponseEntity<ResponseModel> createUser(@Valid @RequestBody UserDto userDto){
        logger.info("call register "+userDto);
        var responseModel = new ResponseModel();

        try{
            var createdUser=userService.registerUser(userDto);
            responseModel.setStatus("success");
            responseModel.setData(createdUser);
            responseModel.setMessage("User created successfully");
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

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> login(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto){
        logger.info("call login "+userLoginRequestDto);
        var responseModel = new ResponseModel();
        try{
            var loginUser=userService.loginUser(userLoginRequestDto);
            responseModel.setStatus("success");

            responseModel.setData(loginUser);
            responseModel.setMessage("User login successfully");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
        catch (ResourceNotFoundException re){
            responseModel.setStatus("failed");
            responseModel.setMessage(re.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PostMapping("/admin/login")
    public ResponseEntity<ResponseModel> adminLogin(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto){
        logger.info("call login "+userLoginRequestDto);
        var responseModel = new ResponseModel();
        try{
            var loginUser=userService.loginAdminUser(userLoginRequestDto);
            responseModel.setStatus("success");

            responseModel.setData(loginUser);
            responseModel.setMessage("Admin login successfully");
            return new ResponseEntity<>(responseModel, HttpStatus.OK);
        }
        catch (ResourceNotFoundException re){
            responseModel.setStatus("failed");
            responseModel.setMessage(re.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            responseModel.setStatus("failed");
            responseModel.setMessage(e.getMessage());
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}

