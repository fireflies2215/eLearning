package com.eLearning.service;

import com.eLearning.dto.user.UserDto;
import com.eLearning.dto.user.UserLoginRequestDto;
import com.eLearning.entity.User;

public interface UserService {
    public UserDto registerUser(UserDto userDto);
    public UserDto loginUser(UserLoginRequestDto user);
}
