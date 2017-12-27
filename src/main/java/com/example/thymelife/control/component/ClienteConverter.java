package com.example.thymelife.control.component;

import com.example.thymelife.model.dto.ClienteDto;
import com.example.thymelife.model.entity.Cliente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administrador on 11/12/17.
 */
@Component("clienteConverter")
public class ClienteConverter {

    public Cliente dtoToEntity(ClienteDto clienteDto){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDto.getId());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellido(clienteDto.getApellido());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setCreadoEn(clienteDto.getCreadoEn());
        return cliente;
    }

    public ClienteDto entityToDto(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellido(cliente.getApellido());
        clienteDto.setEmail(cliente.getEmail());
        clienteDto.setCreadoEn(cliente.getCreadoEn());
        return clienteDto;
    }

    public List<Cliente> dtoToEntity(List<ClienteDto> clienteDtos){
        List<Cliente> clientes = new ArrayList<>();
        for (ClienteDto clienteDto : clienteDtos){
            clientes.add(dtoToEntity(clienteDto));
        }
        return clientes;
    }

    public List<ClienteDto> entityToDto(List<Cliente> clientes){
        List<ClienteDto> clienteDtos = new ArrayList<>();
        for (Cliente cliente : clientes){
            clienteDtos.add(entityToDto(cliente));
        }
        return clienteDtos;
    }


}
