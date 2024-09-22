package com.eLearning.repository;

import com.eLearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUsernameAndPassword(String username, String password);
}
