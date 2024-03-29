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
import com.testviewsoft.dao.util.HibernateUtil;
import com.testviewsoft.modelo.DocumentosIdentidad;
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
import org.hibernate.Session;

/**
 *
 * @author GerlinOTS
 */
@ManagedBean(name="usuariosPaisesBean")
@SessionScoped
public class UsuariosPaisesBean implements Serializable{
    private UsuariosPaises usuarioPais;
    private List<UsuariosPaises> usuariosPaises;
    private List<UsuariosPaises> usuariosPaisesEliminados;
    private String idPais;
    private String idUsuario;
    private String idDocumentoIdentificacion;
    private Usuarios usuarios;
    private Session session=HibernateUtil.getSessionFactory().openSession();
    /**
     * Creates a new instance of UsuariosPaisesBean
     */
    
    public UsuariosPaisesBean() {
        Log("Se crea un objeto UsuarioPaisesBean");
        inicializar();
    }
    private void inicializar(){
        Log("Se INICIALIZARON LOS VALORES");
        idDocumentoIdentificacion=null;
        usuariosPaisesEliminados=new ArrayList<UsuariosPaises>();
        usuarioPais=new UsuariosPaises();
        usuarioPais.setUsuarios(new Usuarios());
        usuariosPaises=new ArrayList<UsuariosPaises>();
        usuarios=new Usuarios();
    }
    public void cancelar(){
        inicializar();
    }
    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public UsuariosPaises getUsuarioPais() {
        return usuarioPais;
    }

    public List<UsuariosPaises> getUsuariosPaisesEliminados() {
        return usuariosPaisesEliminados;
    }

    public void setUsuariosPaisesEliminados(List<UsuariosPaises> usuariosPaisesEliminados) {
        this.usuariosPaisesEliminados = usuariosPaisesEliminados;
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
        Log("PAIS SELECCIONADO-->"+pais.getNombre());
        this.usuarioPais.setPaises(pais);
        this.idPais=null;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
        this.usuarioPais.getUsuarios().setId(Integer.parseInt(idUsuario));
    }

    public String getIdDocumentoIdentificacion() {
        return idDocumentoIdentificacion;
    }

    public void setIdDocumentoIdentificacion(String idDocumentoIdentificacion) {
        this.idDocumentoIdentificacion = idDocumentoIdentificacion;
        DocumentosIdentidad documentoIdentidad=new DocumentosIdentidad();
        documentoIdentidad.setId(Integer.parseInt(idDocumentoIdentificacion));
        this.usuarioPais.getUsuarios().setDocumentosIdentidad(documentoIdentidad);
    }
    
    public void agregarPais(){
        Log("Agregar Pais a la lista usuariospaises");
        this.usuariosPaises.add(usuarioPais);
        Usuarios usuario=usuarioPais.getUsuarios();
        usuarioPais=new UsuariosPaises();
        usuarioPais.setUsuarios(usuario);
    }
    public void editar(){
        Log("METODO editar USUARIOPAIS");
        UsuariosPaisesDao usuariosPaisesDao=new UsuariosPaisesDaoImpl(session);
        UsuariosDao usuariosDao=new UsuariosDaoImpl(session);
        Date tiempo=new Date();
        this.usuarioPais.setEstado(Boolean.TRUE);
        this.usuarioPais.setTiempoEstado(tiempo);
        this.usuarioPais.getUsuarios().setEstado(Boolean.TRUE);
        this.usuarioPais.getUsuarios().setTiempoEstado(tiempo);
        Log(this.usuarioPais.getUsuarios().toString());
        usuariosDao.actualizar(this.usuarioPais.getUsuarios());
        if(usuariosPaises.size()!=0)
            for (int i = 0; i < usuariosPaises.size(); i++) {
                UsuariosPaises usuarioPais = usuariosPaises.get(i);
                usuarioPais.setUsuarios(this.usuarioPais.getUsuarios());
                usuarioPais.setEstado(Boolean.TRUE);
                usuarioPais.setTiempoEstado(tiempo);
                if(usuarioPais.getId()!=null){
                    usuariosPaisesDao.actualizar(usuarioPais);
                }else{
                    usuariosPaisesDao.registrar(usuarioPais);
                }
            }
        if(usuariosPaisesEliminados.size()!=0)
            for (int i = 0; i < usuariosPaisesEliminados.size(); i++) {
                UsuariosPaises usuarioPais = usuariosPaisesEliminados.get(i);
                usuarioPais.setUsuarios(this.usuarioPais.getUsuarios());
                usuarioPais.setEstado(Boolean.FALSE);
                usuarioPais.setTiempoEstado(tiempo);
                usuariosPaisesDao.actualizar(usuarioPais);
            }
        inicializar();
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ACTUALIZACION DEL USUARIO","Fue Actualizado Exitosamente...!"));        
    }
    public void insertar(){
        Log("METODO INSERTAR USUARIOPAIS");
        UsuariosPaisesDao usuariosPaisesDao=new UsuariosPaisesDaoImpl();
        UsuariosDao usuariosDao=new UsuariosDaoImpl();
        Date tiempo=new Date();
        this.usuarioPais.setEstado(Boolean.TRUE);
        this.usuarioPais.setTiempoEstado(tiempo);
        this.usuarioPais.getUsuarios().setEstado(Boolean.TRUE);
        this.usuarioPais.getUsuarios().setTiempoEstado(tiempo);
        usuariosDao.registrar(this.usuarioPais.getUsuarios());
        Usuarios usuarios=usuariosDao.buscarUsuarioPorReferenciaIdentificacion(this.usuarioPais.getUsuarios().getReferenciaIdentificacion());
        if(usuariosPaises.size()!=0)
            for (int i = 0; i < usuariosPaises.size(); i++) {
                UsuariosPaises usuarioPais = usuariosPaises.get(i);
                usuarioPais.setUsuarios(usuarios);
                usuarioPais.setEstado(Boolean.TRUE);
                usuarioPais.setTiempoEstado(tiempo);
                usuariosPaisesDao.registrar(usuarioPais);
                Log(usuarioPais.getPaises().toString());
                System.out.println("AQUI");
            }
        inicializar();
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
    public void eliminarSeleccionPais(Integer id){
        Log("ENTRO A ELIMINARSELECCIONPAIS");
        for (int i = 0; i < usuariosPaises.size(); i++) {
            UsuariosPaises usuarioPais = usuariosPaises.get(i);
            if(usuarioPais.getPaises().getId()==id){
                usuariosPaisesEliminados.add(usuariosPaises.get(i));
                usuariosPaises.remove(i);
                break;
            }
        }
    }
    public void prepararEliminacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL USUARIO");
        UsuariosDao usuariosDao=new UsuariosDaoImpl(session);
        UsuariosPaisesDaoImpl usuariosPaisesDao=new UsuariosPaisesDaoImpl(session);
        usuarioPais.setUsuarios(usuariosDao.buscarPorId(id));
        
        Log(this.usuarioPais.getUsuarios().toString());
        idDocumentoIdentificacion=""+usuarioPais.getUsuarios().getDocumentosIdentidad().getId();
        String ids=""+usuarioPais.getUsuarios().getId();
        usuariosPaises=usuariosPaisesDao.buscarPaisesPorIdUsuario(Integer.parseInt(ids));
        Log(idDocumentoIdentificacion);
    }
    public void eliminar(){
        UsuariosDao usuariosDao=new UsuariosDaoImpl(session);
        UsuariosPaisesDaoImpl usuariosPaisesDao=new UsuariosPaisesDaoImpl(session);
        Date tiempo=new Date();
        usuarioPais.getUsuarios().setEstado(Boolean.FALSE);
        usuarioPais.getUsuarios().setTiempoEstado(tiempo);
        usuariosDao.actualizar(usuarioPais.getUsuarios());
        for (int i = 0; i < usuariosPaises.size(); i++) {
            UsuariosPaises usuarioPais = usuariosPaises.get(i);
            usuarioPais.setUsuarios(this.usuarioPais.getUsuarios());
            usuarioPais.setEstado(Boolean.FALSE);
            usuarioPais.setTiempoEstado(tiempo);
            usuariosPaisesDao.actualizar(usuarioPais);
        }
        inicializar();
        FacesContext context = FacesContext.getCurrentInstance(); 
        context.addMessage("grwForMensajeConfirmacion",new FacesMessage("ELIMINACION DE USUARIO","Fue Eliminado Exitosamente...!"));
    }
    
    public void prepararActualizacion(Integer id){
        Log("METODO PREPARAR ACTUALIZACION DEL USUARIO");
        UsuariosDao usuariosDao=new UsuariosDaoImpl(session);
        UsuariosPaisesDaoImpl usuariosPaisesDao=new UsuariosPaisesDaoImpl(session);
        usuarioPais.setUsuarios(usuariosDao.buscarPorId(id));
        
        Log(this.usuarioPais.getUsuarios().toString());
        idDocumentoIdentificacion=""+usuarioPais.getUsuarios().getDocumentosIdentidad().getId();
        String ids=""+usuarioPais.getUsuarios().getId();
        usuariosPaises=usuariosPaisesDao.buscarPaisesPorIdUsuario(Integer.parseInt(ids));
        Log(idDocumentoIdentificacion);
    }
    private void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }
}
