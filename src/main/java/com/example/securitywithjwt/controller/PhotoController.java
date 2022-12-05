package com.example.securitywithjwt.controller;

import com.example.securitywithjwt.model.Photo;
import com.example.securitywithjwt.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @Value("${project.photo}")
    private String path;

    @PostMapping("/uploadPhoto")
    public ResponseEntity uploadPhoto(@RequestParam("file") MultipartFile file)
    {
        return ResponseEntity.ok(photoService.uploadPhoto(file,path));
    }

    @GetMapping("/getAllPhoto")
    public ResponseEntity getAllPhoto()
    {
        return ResponseEntity.ok(photoService.getAllPhoto());
    }

    @DeleteMapping("/deletePhoto")
    public ResponseEntity deletePhoto(@RequestParam("file") MultipartFile file)
    {
        String message=photoService.deletePhoto(file,path);
        return ResponseEntity.ok(message);
    }
}
