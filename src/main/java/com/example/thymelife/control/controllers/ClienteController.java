package com.example.thymelife.control.controllers;

import com.example.thymelife.control.service.ClienteService;
import com.example.thymelife.model.dto.ClienteDto;
import com.example.thymelife.model.entity.Cliente;
import com.example.thymelife.util.PageRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

/**
 * Created by administrador on 10/11/17.
 */

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    private final Logger log = LoggerFactory.getLogger(getClass());

//    @GetMapping(value = "/upload/{filename:.+}")

    @GetMapping(value = "/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash){
        Cliente cliente = clienteService.findOne(id);
        if (cliente == null){
            flash.addFlashAttribute("error", "El cliente no existe en la aplicacion");
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Detalle cliente: " + cliente.getNombre());
        return "ver";
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model) {

        Pageable pageRequest = new PageRequest(page, 5);

        Page<Cliente> clientes = clienteService.findAll(pageRequest);

        PageRender<Cliente> pageRender = new PageRender<Cliente>("/listar", clientes);
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
        //        model.addAttribute("clientes", clienteService.findAll());

        return "listar";
    }


    @RequestMapping(value = "/form")
    public String crear(Map<String, Object> model){
        Cliente cliente = new Cliente();
        model.put("titulo", "Formulario de Cliente");
        model.put("cliente", cliente);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status){

        if(result.hasErrors()){
            model.addAttribute("titulo", "Formulario de Cliente");
            return "form";
        }

        if (!foto.isEmpty()){
            String uniqueFlieName = UUID.randomUUID().toString() + "_"+foto.getOriginalFilename();
            Path rootPath = Paths.get("upload").resolve(uniqueFlieName);
            Path rootAbsolutePath = rootPath.toAbsolutePath();
            log.info("RootPath: " + rootPath);
            log.info("RootAbsolutePath: " + rootAbsolutePath);
            try {
                Files.copy(foto.getInputStream(), rootAbsolutePath);
                flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFlieName + "'");
                cliente.setFoto(uniqueFlieName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String mensajeFlash = (cliente.getId() != null) ? "Cliente Editado con Exito!!" : "Cliente Creado Con Exito";

        clienteService.save(cliente);
        status.setComplete();
//        flash.addAttribute("success", "Cliente Creado con Exito!");
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:listar";
    }


    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash){

        Cliente cliente = null;
        if (id > 0){
            cliente = clienteService.findOne(id);
            System.out.println(cliente.getId());
            if(cliente == null){
                flash.addFlashAttribute("error", "El cliente no existe");
                return "redirect:/listar";
            }
        }else{
            flash.addAttribute("error", "Cliente ID del cliente es erroneo");
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "form";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash){
        if (id > 0){
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente Eliminado con Exito!");
        }
        return "redirect:/listar";
    }

}
