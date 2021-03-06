package edu.uniminuto.persistencia;
// Generated 12-mar-2015 13:21:11 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Genero generated by hbm2java
 */
public class Genero  implements java.io.Serializable {


     private Short id;
     private String nombre;
     private Set<Disco> discos = new HashSet<Disco>(0);

    public Genero() {
    }

	
    public Genero(String nombre) {
        this.nombre = nombre;
    }
    public Genero(String nombre, Set<Disco> discos) {
       this.nombre = nombre;
       this.discos = discos;
    }
   
    public Short getId() {
        return this.id;
    }
    
    public void setId(Short id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set<Disco> getDiscos() {
        return this.discos;
    }
    
    public void setDiscos(Set<Disco> discos) {
        this.discos = discos;
    }




}


