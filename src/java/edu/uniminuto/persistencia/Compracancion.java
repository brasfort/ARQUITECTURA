package edu.uniminuto.persistencia;
// Generated 12-mar-2015 13:21:11 by Hibernate Tools 4.3.1



/**
 * Compracancion generated by hbm2java
 */
public class Compracancion  implements java.io.Serializable {


     private Integer id;
     private Cancion cancion;
     private Compra compra;
     private int precio;

    public Compracancion() {
    }

    public Compracancion(Cancion cancion, Compra compra, int precio) {
       this.cancion = cancion;
       this.compra = compra;
       this.precio = precio;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Cancion getCancion() {
        return this.cancion;
    }
    
    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }
    public Compra getCompra() {
        return this.compra;
    }
    
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    public int getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(int precio) {
        this.precio = precio;
    }




}

