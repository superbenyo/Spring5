package com.example.thymelife.control.service.impl;

import com.example.thymelife.control.component.ClienteConverter;
import com.example.thymelife.control.service.ClienteService;
import com.example.thymelife.model.dao.ClienteDao;
import com.example.thymelife.model.dto.ClienteDto;
import com.example.thymelife.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by administrador on 1/12/17.
 */

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    @Qualifier("clienteConverter")
    private ClienteConverter clienteConverter;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDto> findAll() {
        return clienteConverter.entityToDto((List<Cliente>)clienteDao.findAll());
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return clienteDao.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDao.delete(id);
    }
}
