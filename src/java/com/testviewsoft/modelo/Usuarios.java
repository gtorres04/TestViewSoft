package com.testviewsoft.modelo;
// Generated 27-oct-2012 18:57:02 by Hibernate Tools 3.2.1.GA


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios  extends EstadoEntidad implements java.io.Serializable {


     private Integer id=0;
     private DocumentosIdentidad documentosIdentidad;
     private String referenciaIdentificacion;
     private String nombre;
     private String apellido;
     private Date fechaNacimiento;
     private String sexo;
     private String mail;
     private Integer nacionalidad;
//     private Boolean estado;
//     private Date tiempoEstado;
     private Set<UsuariosPaises> usuariosPaiseses = new HashSet<UsuariosPaises>();

    public Usuarios() {
    }

	
    public Usuarios(DocumentosIdentidad documentosIdentidad, String referenciaIdentificacion) {
        this.documentosIdentidad = documentosIdentidad;
        this.referenciaIdentificacion = referenciaIdentificacion;
    }
    public Usuarios(DocumentosIdentidad documentosIdentidad, String referenciaIdentificacion, String nombre, String apellido, Date fechaNacimiento, String sexo, String mail, Integer nacionalidad, Boolean estado, Date tiempoEstado, Set<UsuariosPaises> usuariosPaiseses) {
       this.documentosIdentidad = documentosIdentidad;
       this.referenciaIdentificacion = referenciaIdentificacion;
       this.nombre = nombre;
       this.apellido = apellido;
       this.fechaNacimiento = fechaNacimiento;
       this.sexo = sexo;
       this.mail = mail;
       this.nacionalidad = nacionalidad;
//       this.estado = estado;
//       this.tiempoEstado = tiempoEstado;
       super.setEstado(estado);
       super.setTiempoEstado(tiempoEstado);
       this.usuariosPaiseses = usuariosPaiseses;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public DocumentosIdentidad getDocumentosIdentidad() {
        return this.documentosIdentidad;
    }
    
    public void setDocumentosIdentidad(DocumentosIdentidad documentosIdentidad) {
        this.documentosIdentidad = documentosIdentidad;
    }
    public String getReferenciaIdentificacion() {
        return this.referenciaIdentificacion;
    }
    
    public void setReferenciaIdentificacion(String referenciaIdentificacion) {
        this.referenciaIdentificacion = referenciaIdentificacion;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getMail() {
        return this.mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    public Integer getNacionalidad() {
        return this.nacionalidad;
    }
    
    public void setNacionalidad(Integer nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
//    public Boolean getEstado() {
//        return this.estado;
//    }
//    
//    public void setEstado(Boolean estado) {
//        this.estado = estado;
//    }
//    public Date getTiempoEstado() {
//        return this.tiempoEstado;
//    }
//    
//    public void setTiempoEstado(Date tiempoEstado) {
//        this.tiempoEstado = tiempoEstado;
//    }
    public Set<UsuariosPaises> getUsuariosPaiseses() {
        return this.usuariosPaiseses;
    }
    
    public void setUsuariosPaiseses(Set<UsuariosPaises> usuariosPaiseses) {
        this.usuariosPaiseses = usuariosPaiseses;
    }

    @Override
    public String toString() {
        //return "Usuarios{" + "id=" + id + ", documentosIdentidad=" + documentosIdentidad + ", referenciaIdentificacion=" + referenciaIdentificacion + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo + ", mail=" + mail + ", nacionalidad=" + nacionalidad + ", estado=" + estado + ", tiempoEstado=" + tiempoEstado + ", usuariosPaiseses=" + usuariosPaiseses + '}';
        return "Usuarios{" + "id=" + id + ", documentosIdentidad=" + documentosIdentidad + ", referenciaIdentificacion=" + referenciaIdentificacion + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo + ", mail=" + mail + ", nacionalidad=" + nacionalidad + ", estado=" + super.getEstado() + ", tiempoEstado=" + super.getTiempoEstado() + ", usuariosPaiseses=" + usuariosPaiseses + '}';
    }




}


