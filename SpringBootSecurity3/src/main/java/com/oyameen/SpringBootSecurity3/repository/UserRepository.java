package com.oyameen.SpringBootSecurity3.repository;

import com.oyameen.SpringBootSecurity3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserEmail(String userEmail);
}