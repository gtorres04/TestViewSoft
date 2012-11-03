/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.bean;

import com.testviewsoft.dao.UsuariosDao;
import com.testviewsoft.dao.UsuariosDao;
import com.testviewsoft.dao.impl.DocumentosIdentidadDaoImpl;
import com.testviewsoft.dao.impl.UsuariosDaoImpl;
import com.testviewsoft.dao.impl.UsuariosDaoImpl;
import com.testviewsoft.modelo.DocumentosIdentidad;
import com.testviewsoft.modelo.Paises;
import com.testviewsoft.modelo.Usuarios;
import com.testviewsoft.modelo.Usuarios;
import com.testviewsoft.modelo.UsuariosPaises;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;

/**
 *
 * @author GerlinOTS
 */
@ManagedBean(name="usuariosBean")
@RequestScoped
public class UsuariosBean {
    private Usuarios usuario;
    private List<Usuarios> usuarios;
    private String idDocumentoIdentificacion="";
    private DocumentosIdentidadDaoImpl documentosIdentidadDaoImpl;
    
    /**
     * Creates a new instance of UsuariosBean
     */
    public UsuariosBean() {
        Log("se crea un objeto usuariobean");
        usuario=new Usuarios();
        getUsuarios();
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List<Usuarios> getUsuarios() {
        UsuariosDao usuariosDao=new UsuariosDaoImpl();
        usuarios=usuariosDao.buscarTodos();
        return usuarios;
    }

    public String getIdDocumentoIdentificacion() {
        return idDocumentoIdentificacion;
    }

    public void setIdDocumentoIdentificacion(String idDocumentoIdentificacion) {
        this.idDocumentoIdentificacion = idDocumentoIdentificacion;
        DocumentosIdentidad documentoIdentidad=new DocumentosIdentidad();
        documentoIdentidad.setId(Integer.parseInt(idDocumentoIdentificacion));
        this.usuario.setDocumentosIdentidad(documentoIdentidad);
    }

    public void insertar(){
        Log("METODO INSERTAR USUARIO");
        UsuariosDao usuariosDao=new UsuariosDaoImpl();
        Date tiempo=new Date();
        usuario.setEstado(Boolean.TRUE);
        usuario.setTiempoEstado(tiempo);
        Log(usuario.toString());
        usuariosDao.registrar(usuario);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE USUARIO","Fue Actualizado Exitosamente...!"));        
    }
    
    public void actualizar(){
        Log("METODO ACTUALIZAR USUARIO");
        UsuariosDao usuariosDao=new UsuariosDaoImpl();
        Date tiempo=new Date();
        usuario.setEstado(Boolean.TRUE);
        usuario.setTiempoEstado(tiempo);
        usuariosDao.actualizar(usuario);
        Log(usuario.toString());
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DE USUARIO","Fue Actualizado Exitosamente...!"));        
    }
    
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL USUARIO");
        UsuariosDao usuariosDao=new UsuariosDaoImpl();
        usuario=usuariosDao.buscarPorId(id);
        idDocumentoIdentificacion=""+usuario.getDocumentosIdentidad().getId();
    }
    
    public void eliminar(Integer id){
        Log("METODO ELIMINAR USUARIO");
        UsuariosDao usuariosDao=new UsuariosDaoImpl();
        Date tiempo=new Date();
        usuario=usuariosDao.buscarPorId(id);
        usuario.setEstado(Boolean.FALSE);
        usuario.setTiempoEstado(tiempo);
        Log(usuario.toString());
        usuariosDao.actualizar(usuario);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE USUARIO","Fue Eliminado Exitosamente...!"));        
    }
    
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL USUARIO");
        UsuariosDao usuariosDao=new UsuariosDaoImpl();
        usuario=usuariosDao.buscarPorId(id);
    }
    
    public void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }
}
