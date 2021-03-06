package edu.uniminuto.persistencia;
// Generated 12-mar-2015 13:21:11 by Hibernate Tools 4.3.1


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Recopilacion generated by hbm2java
 */
public class Recopilacion  implements java.io.Serializable {


     private Integer id;
     private Persona persona;
     private String nombre;
     private Date fecha;
     private Serializable publica;
     private Set<Recocancion> recocancions = new HashSet<Recocancion>(0);

    public Recopilacion() {
    }

	
    public Recopilacion(Persona persona, String nombre, Date fecha, Serializable publica) {
        this.persona = persona;
        this.nombre = nombre;
        this.fecha = fecha;
        this.publica = publica;
    }
    public Recopilacion(Persona persona, String nombre, Date fecha, Serializable publica, Set<Recocancion> recocancions) {
       this.persona = persona;
       this.nombre = nombre;
       this.fecha = fecha;
       this.publica = publica;
       this.recocancions = recocancions;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Persona getPersona() {
        return this.persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Serializable getPublica() {
        return this.publica;
    }
    
    public void setPublica(Serializable publica) {
        this.publica = publica;
    }
    public Set<Recocancion> getRecocancions() {
        return this.recocancions;
    }
    
    public void setRecocancions(Set<Recocancion> recocancions) {
        this.recocancions = recocancions;
    }




}


