package com.example.demo1.control;

import com.example.demo1.model.Result;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ResourceLoader resourceLoader;
    @PostMapping("/uploaduser")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename=file.getOriginalFilename();
        System.out.print("接收到文件请求：");
        System.out.println(originalFilename);
        String filename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        String projectRootPath = resourceLoader.getResource("classpath:").getFile().getAbsolutePath();
        System.out.println(projectRootPath);
        file.transferTo(new File(projectRootPath + "/../../src/main/resources/static/userpic"+filename));
        return Result.success();
    }
    @PostMapping("/uploadroom")
    public Result<String> uploadroom(MultipartFile file) throws IOException {
        String originalFilename=file.getOriginalFilename();
        System.out.print("接收到文件请求：");
        System.out.println(originalFilename);
        String filename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        String projectRootPath = resourceLoader.getResource("classpath:").getFile().getAbsolutePath();
        System.out.println(projectRootPath);
        file.transferTo(new File(projectRootPath + "/../../src/main/resources/static/roompic"+filename));
        return Result.success();
    }
}
