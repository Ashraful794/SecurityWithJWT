package com.example.securitywithjwt.service;

import com.example.securitywithjwt.model.AuthRequest;
import com.example.securitywithjwt.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User updateUser(User user,Integer id);

    User getUserById(Integer id);

    List<User> getAllUser();

    void deleteUser(Integer userId);

    User login(AuthRequest authRequest);

}
