package com.testviewsoft.modelo;
// Generated 21-oct-2012 20:00:25 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * UsuariosPaises generated by hbm2java
 */
public class UsuariosPaises  implements java.io.Serializable {


     private Integer id;
     private Usuarios usuarios;
     private Paises paises;
     private String gentilicio;
     private Boolean estado;
     private Date tiempoEstado;

    public UsuariosPaises() {
    }

	
    public UsuariosPaises(Usuarios usuarios, Paises paises) {
        this.usuarios = usuarios;
        this.paises = paises;
    }
    public UsuariosPaises(Usuarios usuarios, Paises paises, String gentilicio, Boolean estado, Date tiempoEstado) {
       this.usuarios = usuarios;
       this.paises = paises;
       this.gentilicio = gentilicio;
       this.estado = estado;
       this.tiempoEstado = tiempoEstado;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    public Paises getPaises() {
        return this.paises;
    }
    
    public void setPaises(Paises paises) {
        this.paises = paises;
    }
    public String getGentilicio() {
        return this.gentilicio;
    }
    
    public void setGentilicio(String gentilicio) {
        this.gentilicio = gentilicio;
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




}


