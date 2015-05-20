package edu.uniminuto.persistencia;
// Generated 12-mar-2015 13:21:11 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Persona generated by hbm2java
 */
public class Persona  implements java.io.Serializable, Cloneable {


     private Long id;
     private Rol rol;
     private String nombres;
     private String apellidos;
     private Date nacimiento;
     private String correo;
     private String clave;
     private Set<Compra> compras = new HashSet<Compra>(0);
     private Set<Compradisco> compradiscos = new HashSet<Compradisco>(0);
     private Set<Discopropietario> discopropietarios = new HashSet<Discopropietario>(0);
     private Set<Recopilacion> recopilacions = new HashSet<Recopilacion>(0);
     private Set<Pedido> pedidos = new HashSet<Pedido>(0);

    public Persona() {
    }

	
    public Persona(Rol rol, String nombres, String apellidos, Date nacimiento, String correo, String clave) {
        this.rol = rol;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacimiento = nacimiento;
        this.correo = correo;
        this.clave = clave;
    }
    public Persona(Rol rol, String nombres, String apellidos, Date nacimiento, String correo, String clave, Set<Compra> compras, Set<Compradisco> compradiscos, Set<Discopropietario> discopropietarios, Set<Recopilacion> recopilacions, Set<Pedido> pedidos) {
       this.rol = rol;
       this.nombres = nombres;
       this.apellidos = apellidos;
       this.nacimiento = nacimiento;
       this.correo = correo;
       this.clave = clave;
       this.compras = compras;
       this.compradiscos = compradiscos;
       this.discopropietarios = discopropietarios;
       this.recopilacions = recopilacions;
       this.pedidos = pedidos;
    }
   
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public Date getNacimiento() {
        return this.nacimiento;
    }
    
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    public Set<Compra> getCompras() {
        return this.compras;
    }
    
    public void setCompras(Set<Compra> compras) {
        this.compras = compras;
    }
    public Set<Compradisco> getCompradiscos() {
        return this.compradiscos;
    }
    
    public void setCompradiscos(Set<Compradisco> compradiscos) {
        this.compradiscos = compradiscos;
    }
    public Set<Discopropietario> getDiscopropietarios() {
        return this.discopropietarios;
    }
    
    public void setDiscopropietarios(Set<Discopropietario> discopropietarios) {
        this.discopropietarios = discopropietarios;
    }
    public Set<Recopilacion> getRecopilacions() {
        return this.recopilacions;
    }
    
    public void setRecopilacions(Set<Recopilacion> recopilacions) {
        this.recopilacions = recopilacions;
    }
    public Set<Pedido> getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}


