package com.example.thymelife.control.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by administrador on 9/11/17.
 */
@Controller
public class indexController {

    @Value("${mensaje.hola}")
    private String mensaje;

    @GetMapping("/")
    public String hola(Model model){
        model.addAttribute("mensaje", this.mensaje);
        return "hola";

    }
}
