package com.example.thymelife.control.service.impl;

import com.example.thymelife.control.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by administrador on 11/01/18.
 */

@Service
public class UploadFileServiceImpl implements UploadFileService{

    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final String UPLOAD_FOLDER = "upload";

    @Override
    public Resource load(String fileName) throws MalformedURLException {
        Path pathFoto = getPath(fileName);
        log.info("pathPfot" + pathFoto);
        Resource resource = null;
        resource = new UrlResource(pathFoto.toUri());
        if (!resource.exists()  || !resource.isReadable()){
            throw new RuntimeException("Error: nos se puede cargar la imagen: " + pathFoto.toString());
        }

        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFlieName = UUID.randomUUID().toString() + "_"+file.getOriginalFilename();
        Path rootPath = getPath(uniqueFlieName);
        log.info("RootPath: " + rootPath);
        Files.copy(file.getInputStream(), rootPath);
        return uniqueFlieName;
    }

    @Override
    public boolean delete(String fileName) {
        Path rootPath = getPath(fileName);
        File file = rootPath.toFile();
        if (file.exists() && file.canRead()) {
            if (file.delete()) {
                return true;
            }
        }
        return false;
    }

    public Path getPath(String fileName){
        return Paths.get(UPLOAD_FOLDER).resolve(fileName).toAbsolutePath();
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOAD_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
        Files.createDirectory(Paths.get(UPLOAD_FOLDER));
    }
}
