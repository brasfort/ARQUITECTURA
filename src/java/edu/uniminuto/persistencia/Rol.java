package edu.uniminuto.persistencia;
// Generated 12-mar-2015 13:21:11 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Rol generated by hbm2java
 */
public class Rol  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private Set<Persona> personas = new HashSet<Persona>(0);

    public Rol() {
    }

	
    public Rol(String nombre) {
        this.nombre = nombre;
    }
    public Rol(String nombre, Set<Persona> personas) {
       this.nombre = nombre;
       this.personas = personas;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set<Persona> getPersonas() {
        return this.personas;
    }
    
    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }




}

