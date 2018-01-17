package com.example.thymelife.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by administrador on 16/01/18.
 */
@Entity
@Table(name = "items_facturas")
public class ItemFactura implements Serializable{

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidad;

    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Double calcularImporte(){
        return cantidad.doubleValue() * producto.getPrecio();
    }
}
