package com.example.thymelife.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.file.Paths;

/**
 * Created by administrador on 27/12/17.
 */
@Configuration
public class MvcConfig  extends WebMvcConfigurerAdapter{

    private final Logger log = LoggerFactory.getLogger(getClass());
//TODO Esto que se comento es para cargar la imagen desde la carpeta upload y asi poder visualizar la imagen
//TODO    dentro del aplicativo
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        String resourcePath = Paths.get("upload").toAbsolutePath().toUri().toString();
        log.info("RESOURCE_PATH: ---> " + resourcePath);
        registry.addResourceHandler("/upload/**")
//        registry.addResourceHandler(resourcePath)
//                .addResourceLocations("file:/home/administrador/IdeaProjects/Cursos/Spring-5/upload/");
                .addResourceLocations(resourcePath);
    }
}
