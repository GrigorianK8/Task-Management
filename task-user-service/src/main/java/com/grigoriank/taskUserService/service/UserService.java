package com.grigoriank.taskUserService.service;

import com.grigoriank.taskUserService.entity.User;

import java.util.List;

public interface UserService {

    User getUserProfile(String jwt);

    List<User> getAllUsers();
}
