package com.oyameen.SpringBootSecurity2.repository;

import com.oyameen.SpringBootSecurity2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserEmail(String userEmail);
}
