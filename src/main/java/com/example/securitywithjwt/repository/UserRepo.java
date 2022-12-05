package com.example.securitywithjwt.repository;

import com.example.securitywithjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User findUserByEmail(String email);
    User findUserByName(String name);


}
