package com.example.thymelife.control.controllers;

import com.example.thymelife.control.service.ClienteService;
import com.example.thymelife.control.service.UploadFileService;
import com.example.thymelife.model.entity.Cliente;
import com.example.thymelife.util.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Created by administrador on 10/11/17.
 */

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UploadFileService uploadFileService;



    @GetMapping(value = "/upload/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename){

        Resource resource = null;
        try {
            resource = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                resource.getFilename() + "\"").body(resource);

    }

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
            if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto().length() >0){
                uploadFileService.delete(cliente.getFoto());
            }

            String uniqueFlieName = null;
            try {
                uniqueFlieName = uploadFileService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }

            flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFlieName + "'");
            cliente.setFoto(uniqueFlieName);

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
            Cliente cliente = clienteService.findOne(id);
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente Eliminado con Exito!");

            if(uploadFileService.delete(cliente.getFoto())){
                flash.addFlashAttribute("info", "Foto " + cliente.getFoto() + " se ha eliminado");
            }
        }
        return "redirect:/listar";
    }

}
