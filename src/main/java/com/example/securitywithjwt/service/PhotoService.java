package com.example.securitywithjwt.service;


import com.example.securitywithjwt.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    Photo uploadPhoto(MultipartFile file,String path);

    List<Photo> getAllPhoto();

    String deletePhoto(MultipartFile file,String path);
}
