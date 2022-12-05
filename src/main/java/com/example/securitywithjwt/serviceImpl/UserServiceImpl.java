package com.example.securitywithjwt.serviceImpl;

import com.example.securitywithjwt.model.AuthRequest;
import com.example.securitywithjwt.model.User;
import com.example.securitywithjwt.repository.UserRepo;
import com.example.securitywithjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user)
    {
        User finduser=userRepo.findUserByEmail(user.getEmail());

        if(finduser!=null)
        {
            throw new UsernameNotFoundException("This Email Allready Registered");
        }
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user, Integer id) {
        return null;
    }

    @Override
    public User getUserById(Integer id) {
        return userRepo.findById(id).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepo.findAll();
    }

    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public User login(AuthRequest authRequest) {
        User user=userRepo.findUserByEmail(authRequest.getEmail());

        if (user==null)
        {
            throw new UsernameNotFoundException("User Not Found");
        }

        if(!passwordEncoder.matches(authRequest.getPassword(),user.getPassword()))
        {
            throw new UsernameNotFoundException("Password Doesn't Match");
        }


        return user;

    }
}
