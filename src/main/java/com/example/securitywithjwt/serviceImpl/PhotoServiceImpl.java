package com.example.securitywithjwt.serviceImpl;

import com.example.securitywithjwt.model.Photo;
import com.example.securitywithjwt.model.User;
import com.example.securitywithjwt.repository.PhotoRepo;
import com.example.securitywithjwt.repository.UserRepo;
import com.example.securitywithjwt.security.UserPrinciple;
import com.example.securitywithjwt.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service

public class PhotoServiceImpl implements PhotoService {

    @Autowired
    PhotoRepo photoRepo;
    @Autowired
    UserRepo userRepo;


    @Override
    public Photo uploadPhoto(MultipartFile file,String path)  {

        try
        {
//            String Path_Directory="E:\\Problem Solving New\\SecurityWithJWT\\src\\main\\resources\\static\\photo";
//			String Path_Directory=new ClassPathResource("static/image").getFile().getAbsolutePath();


            String randomId= UUID.randomUUID().toString();


            Files.copy(file.getInputStream(), Paths.get(path+ File.separator+randomId+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            Photo photo=new Photo();

            photo.setFileName(randomId+file.getOriginalFilename());

            User user=user();

            photo.setUser(user);

            photoRepo.save(photo);

            return photo;

        }
        catch(Exception e)
        {
            System.out.println("Error");
        }

        return null;

    }

    @Override
    public List<Photo> getAllPhoto() {
        return photoRepo.findByUser(user());
    }

    @Override
    public String deletePhoto(MultipartFile file, String path) {

        try {

            String filename=file.getOriginalFilename();

            File files = new File(path+'/' +file.getOriginalFilename());
            Photo photo= photoRepo.findByFileName(file.getOriginalFilename());

            if(photo.getUser()!= user())
            {
                throw new UsernameNotFoundException("You Can't Delete this ");
//                throw new UsernameNotFoundException("You can't Delete this Photo") ;
            }

            if(files.delete()) {

                deletePhoto(photo);
                return "Photo Deleted Successfully";

            } else {
                throw new Exception();
            }

        }

        catch (Exception e)
        {
            return e.getMessage();
        }



    }

    private User user()
    {
        SecurityContext context= SecurityContextHolder.getContext();
        UserPrinciple user=(UserPrinciple) context.getAuthentication().getPrincipal();
        return userRepo.getReferenceById(user.getId());
    }

    private void deletePhoto(Photo photo)
    {
        photoRepo.delete(photo);
    }
}
