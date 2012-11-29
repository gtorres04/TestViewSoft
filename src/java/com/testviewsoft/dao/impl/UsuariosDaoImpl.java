/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.UsuariosDao;
import com.testviewsoft.dao.util.HibernateUtil;
import com.testviewsoft.modelo.Usuarios;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author GerlinOTS
 */
public class UsuariosDaoImpl implements UsuariosDao{
    Session session=HibernateUtil.getSessionFactory().openSession();

    public UsuariosDaoImpl(Session session) {
        this.session = session;
    }

    public UsuariosDaoImpl() {
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    public void registrar(Usuarios usuario) {
        try {
            session.beginTransaction();
            session.save(usuario);
            session.beginTransaction().commit();
            
        } catch (Exception e) {
            System.out.println("Error en Insertar "+e.getMessage());
            session.beginTransaction().rollback();
            
        }
    }

    public void actualizar(Usuarios usuario) {
        try {
            session.beginTransaction();
            session.update(usuario);
            session.beginTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en Actualizar "+e.getMessage());
            session.beginTransaction().rollback();
        }
    }

    public void eliminar(Usuarios usuario) {
        try {
            session.beginTransaction();
            session.delete(usuario);
            session.beginTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en Eliminar "+e.getMessage());
            session.beginTransaction().rollback();
        }
    }

    public Usuarios buscarPorId(Integer id) {
        return (Usuarios) session.createQuery("FROM Usuarios usuario WHERE usuario.id="+id+" AND usuario.estado="+Boolean.TRUE).iterate().next();
    }
    public Usuarios buscarUsuarioPorReferenciaIdentificacion(String referenciaIdentificacion) {
        return (Usuarios) session.createQuery("FROM Usuarios usuario WHERE usuario.referenciaIdentificacion="+referenciaIdentificacion+" AND usuario.estado="+Boolean.TRUE).iterate().next();
    }

    public List<Usuarios> buscarTodos() {
        return session.createQuery("FROM Usuarios usuario WHERE usuario.estado="+Boolean.TRUE).list();
    }
    
}
