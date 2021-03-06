package edu.uniminuto.persistencia;
// Generated 12-mar-2015 13:21:11 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Discopropietario generated by hbm2java
 */
public class Discopropietario  implements java.io.Serializable {


     private Integer id;
     private Disco disco;
     private Persona persona;
     private long precio;
     private int vendido;
     private Set<Pedidodisco> pedidodiscos = new HashSet<Pedidodisco>(0);

    public Discopropietario() {
    }

	
    public Discopropietario(Disco disco, Persona persona, long precio, int vendido) {
        this.disco = disco;
        this.persona = persona;
        this.precio = precio;
        this.vendido = vendido;
    }
    public Discopropietario(Disco disco, Persona persona, long precio, int vendido, Set<Pedidodisco> pedidodiscos) {
       this.disco = disco;
       this.persona = persona;
       this.precio = precio;
       this.vendido = vendido;
       this.pedidodiscos = pedidodiscos;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Disco getDisco() {
        return this.disco;
    }
    
    public void setDisco(Disco disco) {
        this.disco = disco;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public long getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(long precio) {
        this.precio = precio;
    }
    public Set<Pedidodisco> getPedidodiscos() {
        return this.pedidodiscos;
    }
    
    public void setPedidodiscos(Set<Pedidodisco> pedidodiscos) {
        this.pedidodiscos = pedidodiscos;
    }

    /**
     * @return the vendido
     */
    public int getVendido() {
        return vendido;
    }

    /**
     * @param vendido the vendido to set
     */
    public void setVendido(int vendido) {
        this.vendido = vendido;
    }




}


