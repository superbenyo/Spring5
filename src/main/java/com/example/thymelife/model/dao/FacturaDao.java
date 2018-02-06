package com.example.thymelife.model.dao;

import com.example.thymelife.model.entity.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by administrador on 23/01/18.
 */
public interface FacturaDao extends CrudRepository<Factura, Long> {

    @Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id =?1")
    public Factura fethcByIdWithClienteWithItemFacturaWithProducto(Long id);

}
