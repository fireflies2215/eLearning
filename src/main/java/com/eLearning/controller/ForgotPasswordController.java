package com.eLearning.controller;


import com.eLearning.dto.ResponseModel;
import com.eLearning.service.UserService;
import com.eLearning.util.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password")
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @PostMapping("/reset-password")
    public ResponseEntity<ResponseModel> resetPassWord(@RequestBody String email)
    {
        try {
            var userDto = userService.findUserByEmail(email);
            String token=userService.generatePaswordResetToken();
            String url="http://localhost:8080/api/password/reset?token=" + token;
            userService.sendEmail(userDto.getEmail(),url);

        }
        catch (ResourceNotFoundException e) {
            logger.error(e.getMessage());
        }

        catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

}

