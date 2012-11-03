/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.bean;

import com.testviewsoft.dao.DocumentosIdentidadDao;
import com.testviewsoft.dao.PaisesDao;
import com.testviewsoft.dao.impl.DocumentosIdentidadDaoImpl;
import com.testviewsoft.dao.impl.PaisesDaoImpl;
import com.testviewsoft.modelo.DocumentosIdentidad;
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
        this.documentosIdentidad=documentosIdentidadDao.buscarTodos();
        
    }
    public List<DocumentosIdentidad> getDocumentosIdentidad() {
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        this.documentosIdentidad=documentosIdentidadDao.buscarTodos();
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
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        Date tiempo=new Date();
        documentoIdentidad=documentosIdentidadDao.buscarPorId(id);
        documentoIdentidad.setEstado(Boolean.FALSE);
        documentoIdentidad.setTiempoEstado(tiempo);
        Log(documentoIdentidad.toString());
        documentosIdentidadDao.actualizar(documentoIdentidad);
    }
    
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL DOCUMENTO IDENTIDAD");
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();
        documentoIdentidad=documentosIdentidadDao.buscarPorId(id);
    }
    public List<SelectItem> listaItemsDocumentosIdentidad(){
        Log("METODO listaItemsPaises() DEL UsuarioPaisesBean");
        DocumentosIdentidadDao documentosIdentidadDao=new DocumentosIdentidadDaoImpl();;
        List<SelectItem> lista=new ArrayList<SelectItem>();
        for (int i = 0; i < documentosIdentidadDao.buscarTodos().size(); i++) {
                lista.add(new SelectItem(""+documentosIdentidadDao.buscarTodos().get(i).getId(), documentosIdentidadDao.buscarTodos().get(i).getNombre()));
        }
        return lista;
    }
    public void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }
}
