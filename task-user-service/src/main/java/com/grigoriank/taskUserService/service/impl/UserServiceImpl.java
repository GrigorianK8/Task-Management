package com.grigoriank.taskUserService.service.impl;

import com.grigoriank.taskUserService.entity.User;
import com.grigoriank.taskUserService.repository.UserRepository;
import com.grigoriank.taskUserService.service.UserService;
import com.grigoriank.taskUserService.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public User getUserProfile(String jwt) {
        String email = jwtTokenUtil.getEmailFromJwtToken(jwt);
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
