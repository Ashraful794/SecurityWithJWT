package com.example.securitywithjwt.controller;

import com.example.securitywithjwt.model.AuthRequest;
import com.example.securitywithjwt.model.User;
import com.example.securitywithjwt.security.CustomeUserDetails;
import com.example.securitywithjwt.security.UserPrinciple;
import com.example.securitywithjwt.service.JWTUtil;
import com.example.securitywithjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomeUserDetails customeUserDetails;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(null);
        User userr=this.userService.createUser(user);
        return ResponseEntity.ok(userr);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest authRequest)
    {
        User user=this.userService.login(authRequest);
        final UserDetails userDetails = customeUserDetails.loadUserByUsername(user.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity getAllUser()
    {
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @GetMapping("/user")
    public ResponseEntity user()
    {
        SecurityContext context= SecurityContextHolder.getContext();
        UserPrinciple user=(UserPrinciple) context.getAuthentication().getPrincipal();

        return ResponseEntity.ok(user);
    }
    @GetMapping("/admin")
    public ResponseEntity admin()
    {
        return ResponseEntity.ok("Admin");
    }

    @GetMapping("/role")
    public ResponseEntity role()
    {
        SecurityContext context= SecurityContextHolder.getContext();
        UserPrinciple user=(UserPrinciple) context.getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.getUserById(user.getId()).getRole());
    }
    @GetMapping("/example")
    public ResponseEntity currentCustomProperty(Authentication authentication ) {

        UserPrinciple user=(UserPrinciple)authentication.getPrincipal();

        return ResponseEntity.ok(user);
    }

}
