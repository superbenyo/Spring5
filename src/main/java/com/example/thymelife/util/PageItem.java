package com.example.thymelife.util;

/**
 * Created by administrador on 14/12/17.
 */
public class PageItem {

    private int numero;
    private boolean actual;
    public PageItem(int numero, boolean actual) {
        this.numero = numero;
        this.actual = actual;
    }
    public int getNumero() {
        return numero;
    }
    public boolean isActual() {
        return actual;
    }


}

