package edu.uniminuto.persistencia;
// Generated 12-mar-2015 13:21:11 by Hibernate Tools 4.3.1



/**
 * Recocancion generated by hbm2java
 */
public class Recocancion  implements java.io.Serializable {


     private Integer id;
     private Cancion cancion;
     private Recopilacion recopilacion;

    public Recocancion() {
    }

    public Recocancion(Cancion cancion, Recopilacion recopilacion) {
       this.cancion = cancion;
       this.recopilacion = recopilacion;
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
    public Recopilacion getRecopilacion() {
        return this.recopilacion;
    }
    
    public void setRecopilacion(Recopilacion recopilacion) {
        this.recopilacion = recopilacion;
    }




}


