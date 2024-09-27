package com.eLearning.repository;

import com.eLearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUsernameAndPasswordAndRole(String username, String password,String role);

}
