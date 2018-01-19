package com.example.thymelife.control.service;

import com.example.thymelife.model.dto.ClienteDto;
import com.example.thymelife.model.entity.Cliente;
import com.example.thymelife.model.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by administrador on 1/12/17.
 */
public interface ClienteService {
    public List<ClienteDto> findAll();
    public Page<Cliente> findAll(Pageable pageable);
    public void save(Cliente cliente);
    public Cliente findOne(Long id);
    public void delete(Long id);
    List<Producto> findByName(String term);
}
