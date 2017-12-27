package com.example.thymelife.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by administrador on 27/12/17.
 */
@Configuration
public class MvcConfig  extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/home/administrador/IdeaProjects/Cursos/Spring-5/upload/");
    }
}
