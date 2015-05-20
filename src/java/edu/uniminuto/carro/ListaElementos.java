/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.carro;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jhon Gómez
 */
public class ListaElementos {
    private Integer id;
    private List<Elemento> elementos = new ArrayList<>();

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the elementos
     */
    public List<Elemento> getElementos() {
        return elementos;
    }

    /**
     * @param elementos the elementos to set
     */
    public void setElementos(List<Elemento> elementos) {
        this.elementos = elementos;
    }
    
    public void agregarElemento(Elemento elemento) {
        
    }
    
    public int getTotal() {
        int total = 0;
        for (Elemento elemento : elementos) {
            total += elemento.getPrecio();
        }
        return total;
    }
    
    public int size() {
        return elementos.size();
    }
    
    public boolean isEmpty() {
        return elementos.isEmpty();
    }
    
    
    public boolean hayDiscos() {
        for (Elemento elemento : elementos) {
            if (elemento.getDiscoId() != null) {
                return true;
            }
        }
        return false;
    }
}
