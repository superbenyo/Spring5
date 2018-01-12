package com.example.thymelife.control.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by administrador on 11/01/18.
 */
public interface UploadFileService {

    Resource load(String fileName) throws MalformedURLException;
    String copy(MultipartFile file) throws IOException;
    boolean delete(String filename);
    void deleteAll();
    void init() throws IOException;
}
