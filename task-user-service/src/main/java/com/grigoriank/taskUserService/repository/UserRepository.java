package com.grigoriank.taskUserService.repository;

import com.grigoriank.taskUserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
