package com.commerce.ecom.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String fileName= file.getOriginalFilename();

        String randomId = UUID.randomUUID().toString();
        String newFileName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
        String filePath = path + File.separator + newFileName;

        File folder= new File(path);
        if(!folder.exists()){
            folder.mkdirs();
        }

        Files.copy(file.getInputStream(), new File(filePath).toPath());

        return newFileName;
    }
}
