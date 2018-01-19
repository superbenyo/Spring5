package com.example.thymelife.control.controllers;

import com.example.thymelife.control.service.ClienteService;
import com.example.thymelife.model.entity.Cliente;
import com.example.thymelife.model.entity.Factura;
import com.example.thymelife.model.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * Created by administrador on 17/01/18.
 */
@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/form/{clienteId}")
    public String crear(@PathVariable(value = "clienteId") Long clienteId, Map<String, Object> model,
                        RedirectAttributes flash){
        Cliente cliente = clienteService.findOne(clienteId);
        if (cliente == null){
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }

        Factura factura = new Factura();

        factura.setCliente(cliente);

        model.put("factura", factura);
        model.put("titulo", "Crear factura");

        return "/factura/form";
    }

    @GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
    public @ResponseBody List<Producto> cargarProductos(@PathVariable String term){
        return clienteService.findByName(term);
    }
}