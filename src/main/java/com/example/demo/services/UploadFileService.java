package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileService {

    private String upload_folder = "./src/main/resources/static/files/";

    public String saveFile(MultipartFile file) throws IOException {
    	System.out.println("UploadFileService -inicio");
    	String ruta = upload_folder + file.getOriginalFilename();
    	System.out.println("ruta: " + ruta);
        if(!file.isEmpty()){
        	System.out.println("Tiene datos. Copiamos.");
            byte[] bytes = file.getBytes();
            Path path = Paths.get(ruta);
            Files.write(path,bytes);
        }
        
        return ruta;
    }
}
