package com.example.thymelife.control.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.thymelife.control.service.ClienteService;
import com.example.thymelife.model.entity.Cliente;
import com.example.thymelife.model.entity.Factura;
import com.example.thymelife.model.entity.ItemFactura;
import com.example.thymelife.model.entity.Producto;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



/**
 * Created by administrador on 17/01/18.
 */
@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") long id, Model model, RedirectAttributes flash){
        Factura factura = clienteService.findFacturaById(id);

        if (factura == null){
            flash.addFlashAttribute("error", "La factyura no existe en la base de datos");
            return "redirect:/listar";
        }

        model.addAttribute("factura", factura);
        model.addAttribute("titulo", "factura".concat(factura.getDescripcion()));
        return "factura/ver";


    }

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

        return "factura/form";
    }

    @GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
    public @ResponseBody List<Producto> cargarProductos(@PathVariable String term){
        return clienteService.findByName(term);
    }

    @PostMapping("/form")
    public String guardar(@Valid Factura factura, BindingResult result, Model model,
                          @RequestParam(name = "item_id[]", required = false) Long[] itemId,
                          @RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
                          SessionStatus status) {


        if (result.hasErrors()) {
            model.addAttribute("titulo", "Crear Factura");
            return "factura/form";
        }

        if (itemId == null || itemId.length == 0) {
            model.addAttribute("titulo", "Crear Factura");
            model.addAttribute("error", "Error: La factura NO puede no tener líneas!");
            return "factura/form";
        }


        for (int i = 0 ; i < itemId.length; i++){
            Producto producto = clienteService.findProductoById(itemId[i]);
            ItemFactura itemFactura = new ItemFactura();
            itemFactura.setCantidad(cantidad[i]);;
            itemFactura.setProducto(producto);
            factura.addItemFactura(itemFactura);
            log.info("ID: " + itemId[i].toString() + " CANTIDAD: " + cantidad[i].toString());
        }
        clienteService.saveFactura(factura);
        status.setComplete();
        flash.addFlashAttribute("success", "Factura creada con exito!");

        return "redirect:/ver/" + factura.getCliente().getId();
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id")Long id, RedirectAttributes flash){
        Factura factura = clienteService.findFacturaById(id);

        if(factura != null){
            clienteService.deleteFactura(id);
            flash.addFlashAttribute("success", "Factura Eliminada con Exito!");
            return "redirect:/ver/ " + factura.getCliente().getId();
        }
        flash.addFlashAttribute("error", "La factura no existe en la base de datos no se pudo eliminar");
        return"redirect:/listar";
    }

}
