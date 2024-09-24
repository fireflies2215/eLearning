package com.eLearning.service;

import com.eLearning.dto.user.UserDto;
import com.eLearning.dto.user.UserLoginRequestDto;
import com.eLearning.entity.User;

public interface UserService {
    public UserDto registerUser(UserDto userDto);
    public UserDto loginUser(UserLoginRequestDto user);
    public UserDto loginAdminUser(UserLoginRequestDto user);
    public String generatePaswordResetToken();
    public UserDto findUserByEmail(String email);
    public void sendEmail(String to,String resetUrl);
//    void updatePassword(User user, String newPassword);

}
