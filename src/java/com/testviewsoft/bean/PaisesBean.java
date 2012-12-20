/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.bean;

import com.testviewsoft.dao.PaisesDao;
import com.testviewsoft.dao.UsuariosPaisesDao;
import com.testviewsoft.dao.impl.PaisesDaoImpl;
import com.testviewsoft.dao.impl.UsuariosPaisesDaoImpl;
import com.testviewsoft.dao.util.HibernateUtil;
import com.testviewsoft.modelo.Paises;
import com.testviewsoft.modelo.UsuariosPaises;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author GerlinOTS
 */
@ManagedBean
@RequestScoped
public class PaisesBean {
    private Paises pais;
    private List<Paises> paises;
    //PaisesDao paisesDao=null;
    /**
     * Creates a new instance of PaisesBean
     */
    public PaisesBean() {
        Log("se crea un objeto paisbean");
        pais=new Paises();
        getPaises();
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public List<Paises> getPaises() {
        PaisesDaoImpl paisesDao=new PaisesDaoImpl();
        paises=paisesDao.buscarTodos();
        return paises;
    }
    
    public void insertar(){
        Log("METODO INSERTAR PAIS");
        PaisesDao paisesDao=new PaisesDaoImpl();
        Date tiempo=new Date();
        pais.setEstado(Boolean.TRUE);
        pais.setTiempoEstado(tiempo);
        paisesDao.registrar(pais);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE PAIS","Fue Agregado Exitosamente...!"));        
    }
    
    public void actualizar(){
        Log("METODO ACTUALIZAR PAIS");
        PaisesDao paisesDao=new PaisesDaoImpl();
        Date tiempo=new Date();
        pais.setTiempoEstado(tiempo);
        pais.setEstado(Boolean.TRUE);
        paisesDao.actualizar(pais);
        Log(pais.toString());
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DE PAIS","Fue Actualizado Exitosamente...!"));        
    }
    
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL PAIS");
        PaisesDaoImpl paisesDao=new PaisesDaoImpl();
        pais=paisesDao.buscarPorId(id);
    }
    
    public void eliminar(Integer id){
        Log("METODO ELIMINAR PAIS");
        Date tiempo=new Date();
        PaisesDao paisesDao=new PaisesDaoImpl();
        pais=paisesDao.buscarPorId(id);
        List<UsuariosPaises> listaUsuariosPaisesRelacionadosPais=listaUsuariosPaisesEstadoTRUERelacionadosConPaises(pais);
        FacesContext context = FacesContext.getCurrentInstance(); 
        if(listaUsuariosPaisesRelacionadosPais.size()==0){
            pais.setEstado(Boolean.FALSE);
            pais.setTiempoEstado(tiempo);
            paisesDao.actualizar(pais);
            context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE PAIS","Fue Actualizado Exitosamente...!"));
        }else{
            context.addMessage("grwForMensajeConfirmacion",new FacesMessage(FacesMessage.SEVERITY_ERROR, "IMPOSIBLE ELIMINAR","Este Pais Esta relacionado con "+listaUsuariosPaisesRelacionadosPais.size()+" Registros de Usuarios"));
        }
    }
    
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ELIMINACION DEL PAIS");
        PaisesDao paisesDao=new PaisesDaoImpl();
        pais=paisesDao.buscarPorId(id);
    }
    
    public List<UsuariosPaises> listaUsuariosPaisesEstadoTRUERelacionadosConPaises(Paises paises){
        List<UsuariosPaises> listaUsuariosPaisesRelacionadosConPaises=new ArrayList<UsuariosPaises>();
        UsuariosPaisesDao usuariosDao=new UsuariosPaisesDaoImpl();
        List<UsuariosPaises> listaUsuariosPaises;
        listaUsuariosPaises = usuariosDao.buscarTodosEstadoTRUE();
        for (int i = 0; i < listaUsuariosPaises.size(); i++) {
            UsuariosPaises usuariosPaises = listaUsuariosPaises.get(i);
            Log(""+usuariosPaises.getPaises().getId()+"=="+paises.getId());
            Log(""+(usuariosPaises.getPaises().getId().equals(paises.getId())));
            if(usuariosPaises.getPaises().getId().equals(paises.getId())){
                listaUsuariosPaisesRelacionadosConPaises.add(usuariosPaises);
            }
        }
        return listaUsuariosPaisesRelacionadosConPaises;
    }
    
    public void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }    
}
