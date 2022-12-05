package com.example.securitywithjwt.repository;

import com.example.securitywithjwt.model.Photo;
import com.example.securitywithjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepo extends JpaRepository<Photo,Integer> {

    List<Photo> findByUser(User user);
    Photo findByFileName(String fileName);
}
