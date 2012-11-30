/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.bean;

import com.testviewsoft.dao.DocumentosIdentidadDao;
import com.testviewsoft.dao.UsuariosDao;
import com.testviewsoft.dao.impl.DocumentosIdentidadDaoImpl;
import com.testviewsoft.dao.impl.UsuariosDaoImpl;
import com.testviewsoft.modelo.DocumentosIdentidad;
import com.testviewsoft.modelo.Usuarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author GerlinOTS
 */
@ManagedBean
@RequestScoped
public class DocumentosIdentidadBean {
    private DocumentosIdentidad documentoIdentidad;
    private List<DocumentosIdentidad> documentosIdentidad;
    /**
     * Creates a new instance of DocumentosIdentidadBean
     */
    public DocumentosIdentidadBean() {
        Log("se crea un objeto documentoIdentidadbean");
        documentoIdentidad=new DocumentosIdentidad();
        getDocumentosIdentidad();
    }

    public DocumentosIdentidad getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(DocumentosIdentidad documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }
    public void cargarListaDocumentosIdentidad(){
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        this.documentosIdentidad=documentosIdentidadDao.buscarTodosEstadoTRUE();
        
    }
    public List<DocumentosIdentidad> getDocumentosIdentidad() {
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        this.documentosIdentidad=documentosIdentidadDao.buscarTodosEstadoTRUE();
        return documentosIdentidad;
    }
    
    public void insertar(){
        Log("METODO INSERTAR DOCUMENTO IDENTIDAD");
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        Date tiempo=new Date();
        documentoIdentidad.setEstado(Boolean.TRUE);
        documentoIdentidad.setTiempoEstado(tiempo);
        documentosIdentidadDao.registrar(documentoIdentidad);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE DOCUMENTO DE IDENTIDAD","Fue Agregado Exitosamente...!"));        
    }
    
    public void actualizar(){
        Log("METODO ACTUALIZAR DOCUMENTO IDENTIDAD");
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        Date tiempo=new Date();
        documentoIdentidad.setEstado(Boolean.TRUE);
        documentoIdentidad.setTiempoEstado(tiempo);
        documentosIdentidadDao.actualizar(documentoIdentidad);
        Log(documentoIdentidad.toString());
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DE DOCUMENTO DE IDENTIDAD","Fue Actualizado Exitosamente...!"));        
    }
    
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL DOCUMENTO IDENTIDAD");
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        documentoIdentidad=documentosIdentidadDao.buscarPorId(id);
    }
    
    public void eliminar(Integer id){
        Log("METODO ELIMINAR DOCUMENTO IDENTIDAD");
        FacesContext context = FacesContext.getCurrentInstance(); 
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        Date tiempo=new Date();
        documentoIdentidad=documentosIdentidadDao.buscarPorId(id);
        List<Usuarios> listaUsuariosRelacionadosDocumentoIdentidad=listaUsuariosEstadoTRUERelacionadosConDocumentoIdentidad(documentoIdentidad);
        if(listaUsuariosRelacionadosDocumentoIdentidad.size()==0){
            documentoIdentidad.setEstado(Boolean.FALSE);
            documentoIdentidad.setTiempoEstado(tiempo);
            Log(documentoIdentidad.toString());
            documentosIdentidadDao.actualizar(documentoIdentidad);
            context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE DOCUMENTO DE IDENTIDAD","Fue Eliminado Exitosamente...!"));
        }else{
            context.addMessage("grwForMensajeConfirmacion",new FacesMessage(FacesMessage.SEVERITY_ERROR, "IMPOSIBLE","Esta Documento Esta relacionado con "+listaUsuariosRelacionadosDocumentoIdentidad.size()+" Registros de Usuarios"));
        }
    }
    
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL DOCUMENTO IDENTIDAD");
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        documentoIdentidad=documentosIdentidadDao.buscarPorId(id);
    }
    public List<SelectItem> listaItemsDocumentosIdentidad(){
        Log("METODO listaItemsDOCUMENTOSIDENTIDAD() DEL DOCUMENTOSIDENTIDADBean");
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();;
        List<SelectItem> lista=new ArrayList<SelectItem>();
        for (int i = 0; i < documentosIdentidadDao.buscarTodosEstadoTRUE().size(); i++) {
                lista.add(new SelectItem(""+documentosIdentidadDao.buscarTodosEstadoTRUE().get(i).getId(), documentosIdentidadDao.buscarTodosEstadoTRUE().get(i).getNombre()));
        }
        return lista;
    }
    public List<Usuarios> listaUsuariosEstadoTRUERelacionadosConDocumentoIdentidad(DocumentosIdentidad documentoIdentidad){
        List<Usuarios> listaUsuariosRelacionadosConDocumentoIdentidad=new ArrayList<Usuarios>();
        UsuariosDao usuariosDao=new UsuariosDaoImpl();
        List<Usuarios> listaUsuarios;
        listaUsuarios = usuariosDao.buscarTodosEstadoTRUE();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuarios usuarios = listaUsuarios.get(i);
            Log(""+usuarios.getDocumentosIdentidad().getId()+"=="+documentoIdentidad.getId());
            Log(""+(usuarios.getDocumentosIdentidad().getId().equals(documentoIdentidad.getId())));
            if(usuarios.getDocumentosIdentidad().getId().equals(documentoIdentidad.getId())){
                listaUsuariosRelacionadosConDocumentoIdentidad.add(usuarios);
            }
        }
        return listaUsuariosRelacionadosConDocumentoIdentidad;
    }
    public void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }
}
