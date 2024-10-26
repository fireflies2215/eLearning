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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private  UserRepository userRepository;

    private ModelMapper modelMapper=new ModelMapper();




    @Override
    public User registerUser(User user) {

        try{
            var createdUser=userRepository.save(user);

            return createdUser;
        }
        catch (DataIntegrityViolationException ex){

            throw new DataIntegrityViolationException("User already exists");
        }
        catch (Exception e){

            throw new RuntimeException(e.getMessage());

        }

    }


    @Override
    public User loginUser(UserLoginRequestDto userLoginRequestDto) {

        List<String> roles= Arrays.asList("student","faculty");

        var user=userRepository.findByEmailAndRoleIn(userLoginRequestDto.getUsername(),roles);

        if(user==null){
            throw new ResourceNotFoundException("user not found");
        }


        System.out.println(user.getPassword());
        System.out.println(userLoginRequestDto.getPassword());
        if(!(user.getPassword()).equals(userLoginRequestDto.getPassword())){
            throw new ResourceNotFoundException("Password is incorrect");
        }

        return user;
    }

    public User loginAdminUser(UserLoginRequestDto userLoginRequestDto) {


        User user=modelMapper.map(userLoginRequestDto, User.class);



        var loginUser=userRepository.findByEmailAndRole(userLoginRequestDto.getUsername(),"admin");

        if(loginUser.isEmpty()){
            throw new ResourceNotFoundException("admin not found");
        }

        if(!(loginUser.get().getPassword()).equals(userLoginRequestDto.getPassword())){
            throw new ResourceNotFoundException("Password is incorrect");
        }


        return loginUser.get();
    }

    @Override
    public User updateUser(User user) {
        try{
            var updatedUser=userRepository.save(user);

            return updatedUser;
        }
        catch (Exception e){

            throw new RuntimeException("Error While updating "+user.getRole()+" details : "+e.getMessage());

        }
    }




}
