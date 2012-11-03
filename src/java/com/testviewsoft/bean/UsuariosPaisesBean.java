/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.bean;

import com.testviewsoft.dao.PaisesDao;
import com.testviewsoft.dao.UsuariosDao;
import com.testviewsoft.dao.UsuariosPaisesDao;
import com.testviewsoft.dao.impl.PaisesDaoImpl;
import com.testviewsoft.dao.impl.UsuariosDaoImpl;
import com.testviewsoft.dao.impl.UsuariosPaisesDaoImpl;
import com.testviewsoft.modelo.Paises;
import com.testviewsoft.modelo.Usuarios;
import com.testviewsoft.modelo.UsuariosPaises;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author GerlinOTS
 */
@ManagedBean(name="usuariosPaisesBean")
@SessionScoped
public class UsuariosPaisesBean implements Serializable{
    private UsuariosPaises usuarioPais;
    private List<UsuariosPaises> usuariosPaises;
    private String idPais;
    private String idUsuario;
    /**
     * Creates a new instance of UsuariosPaisesBean
     */
    public UsuariosPaisesBean() {
        Log("Se crea un objeto UsuarioPaisesBean");
        usuarioPais=new UsuariosPaises();
        usuariosPaises=new ArrayList<UsuariosPaises>();
        
    }

    public UsuariosPaises getUsuarioPais() {
        return usuarioPais;
    }

    public void setUsuarioPais(UsuariosPaises usuarioPais) {
        this.usuarioPais = usuarioPais;
    }

    public List<UsuariosPaises> getUsuariosPaises() {
//        UsuariosPaisesDao usuariosPaisesDao=new UsuariosPaisesDaoImpl();
//        usuariosPaises=usuariosPaisesDao.buscarTodos();
        return usuariosPaises;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
        PaisesDao paisDao=new PaisesDaoImpl();
        Paises pais=paisDao.buscarPorId(Integer.parseInt(idPais));
        this.usuarioPais.setPaises(pais);
        this.idPais=null;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
        Usuarios usuario=new Usuarios();
        usuario.setId(Integer.parseInt(idUsuario));
        this.usuarioPais.setUsuarios(usuario);
    }
    public void agregarPais(){
        Log("Agregar Pais a la lista usuariospaises");
        this.usuariosPaises.add(usuarioPais);
        usuarioPais=null;
        usuarioPais=new UsuariosPaises();
    }
    public void insertar(){
        Log("METODO INSERTAR USUARIOPAIS");
        UsuariosPaisesDao usuariosPaisesDao=new UsuariosPaisesDaoImpl();
        Date tiempo=new Date();
        this.usuarioPais.setEstado(Boolean.TRUE);
        this.usuarioPais.setTiempoEstado(tiempo);
        Log(usuarioPais.toString());
        usuariosPaisesDao.registrar(usuarioPais);
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("REGISTRO DE USUARIO PAISES","Fue Registrado Exitosamente...!"));        
    }
    public List<SelectItem> listaItemsPaises(){
        Log("METODO listaItemsPaises() DEL UsuarioPaisesBean");
        PaisesDao paisesDao=new PaisesDaoImpl();
        List<SelectItem> lista=new ArrayList<SelectItem>();
        for (int i = 0; i < paisesDao.buscarTodos().size(); i++) {
            if(consultarExistencia(""+paisesDao.buscarTodos().get(i).getId()))
                lista.add(new SelectItem(""+paisesDao.buscarTodos().get(i).getId(), paisesDao.buscarTodos().get(i).getNombre()));
        }
        return lista;
    }
    public boolean consultarExistencia(String Id){
        boolean agrega=true;
        for (int i = 0; i < usuariosPaises.size(); i++) {
            UsuariosPaises usuariosPaises1 = usuariosPaises.get(i);
            String id=""+usuariosPaises1.getPaises().getId();
            if(id.equals(Id)){
                agrega= false;
            }
        }
        return agrega;
    }
    public void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }
}
