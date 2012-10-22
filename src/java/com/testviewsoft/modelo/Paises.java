package com.testviewsoft.modelo;
// Generated 21-oct-2012 20:00:25 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Paises generated by hbm2java
 */
public class Paises  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private Boolean estado;
     private Date tiempoEstado;
     private Set<UsuariosPaises> usuariosPaiseses = new HashSet<UsuariosPaises>(0);

    public Paises() {
    }

	
    public Paises(String nombre) {
        this.nombre = nombre;
    }
    public Paises(String nombre, Boolean estado, Date tiempoEstado, Set<UsuariosPaises> usuariosPaiseses) {
       this.nombre = nombre;
       this.estado = estado;
       this.tiempoEstado = tiempoEstado;
       this.usuariosPaiseses = usuariosPaiseses;
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
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public Date getTiempoEstado() {
        return this.tiempoEstado;
    }
    
    public void setTiempoEstado(Date tiempoEstado) {
        this.tiempoEstado = tiempoEstado;
    }
    public Set<UsuariosPaises> getUsuariosPaiseses() {
        return this.usuariosPaiseses;
    }
    
    public void setUsuariosPaiseses(Set<UsuariosPaises> usuariosPaiseses) {
        this.usuariosPaiseses = usuariosPaiseses;
    }




}


