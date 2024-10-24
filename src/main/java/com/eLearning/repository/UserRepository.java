package com.eLearning.repository;

import com.eLearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    User findByEmailAndRoleIn(String email,List<String> roles);

    Optional<User> findByEmailAndRole(String email, String role);

    List<User> findByRole(String role);


}
