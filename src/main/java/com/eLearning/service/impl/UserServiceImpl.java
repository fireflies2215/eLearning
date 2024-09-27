package com.eLearning.service.impl;

import com.eLearning.dto.user.UserDto;
import com.eLearning.dto.user.UserLoginRequestDto;
import com.eLearning.entity.User;
import com.eLearning.repository.UserRepository;
import com.eLearning.service.UserService;
import com.eLearning.util.ResourceNotFoundException;
import jdk.jshell.spi.ExecutionControl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private  UserRepository userRepository;

    private ModelMapper modelMapper=new ModelMapper();




    @Override
    public UserDto registerUser(UserDto userDto) {
        logger.info("call registerUser() method"+userDto.toString());

        User user=modelMapper.map(userDto, User.class);

        try{
            var createdUser=userRepository.save(user);
            logger.info("after user creation:"+createdUser);
            return modelMapper.map(createdUser,UserDto.class);
        }
        catch (DataIntegrityViolationException ex){
            logger.error(ex.getMessage());
            throw new DataIntegrityViolationException("User already exists");
        }
        catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());

        }

    }

    @Override
    public UserDto loginUser(UserLoginRequestDto userLoginRequestDto) {
        logger.info("loginUser() method"+userLoginRequestDto.toString());

        User user=modelMapper.map(userLoginRequestDto, User.class);

        logger.info("before user login:"+user.toString());

        var loginUser=userRepository.findByUsernameAndPasswordAndRole(userLoginRequestDto.getUsername(),userLoginRequestDto.getPassword(),"user");

        if(loginUser==null){
            throw new ResourceNotFoundException("user not found");
        }

        logger.info("logging successfully :"+loginUser);
        return modelMapper.map(loginUser,UserDto.class);
    }

    public UserDto loginAdminUser(UserLoginRequestDto userLoginRequestDto) {
        logger.info("loginUser() method"+userLoginRequestDto.toString());

        User user=modelMapper.map(userLoginRequestDto, User.class);

        logger.info("before user login:"+user.toString());

        var loginUser=userRepository.findByUsernameAndPasswordAndRole(userLoginRequestDto.getUsername(),userLoginRequestDto.getPassword(),"admin");

        if(loginUser==null){
            throw new ResourceNotFoundException("admin not found");
        }

        logger.info("logging successfully :"+loginUser);
        return modelMapper.map(loginUser,UserDto.class);
    }






}
