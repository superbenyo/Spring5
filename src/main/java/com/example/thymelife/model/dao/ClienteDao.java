package com.example.thymelife.model.dao;

import com.example.thymelife.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by administrador on 4/12/17.
 */
public interface ClienteDao extends PagingAndSortingRepository<Cliente, Long> {
//public interface ClienteDaoB extends CrudRepository<Cliente, Long>{

}
