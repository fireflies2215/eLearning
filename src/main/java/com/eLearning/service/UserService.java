package com.eLearning.service;

import com.eLearning.dto.user.UserDto;
import com.eLearning.dto.user.UserLoginRequestDto;
import com.eLearning.entity.User;

import java.util.Optional;

public interface UserService {
    public User registerUser(User user);
    public User loginUser(UserLoginRequestDto userLoginRequestDto);
    public User loginAdminUser(UserLoginRequestDto userLoginRequestDto);

    public User updateUser(User user);
//    void updatePassword(User user, String newPassword);

}
