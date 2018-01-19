package com.example.thymelife.model.dao;

import com.example.thymelife.model.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by administrador on 18/01/18.
 */
public interface ProductoDao extends CrudRepository<Producto, Long> {

    @Query("select p from Producto p where p.nombre like %?1%")
    List<Producto> findByName(String term);

    List<Producto> findByNombreLikeIgnoreCase(String term);
}
