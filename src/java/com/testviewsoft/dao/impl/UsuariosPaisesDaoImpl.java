/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.UsuariosPaisesDao;
import com.testviewsoft.dao.util.HibernateUtil;
import com.testviewsoft.modelo.UsuariosPaises;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author GerlinOTS
 */
public class UsuariosPaisesDaoImpl implements UsuariosPaisesDao{
    private Session session=HibernateUtil.getSessionFactory().openSession();

    public void setSession(Session session) {
        this.session = session;
    }

    public UsuariosPaisesDaoImpl(Session session) {
        this.session = session;
    }

    public UsuariosPaisesDaoImpl() {
    }
    
    public void registrar(UsuariosPaises usuarioPais) {
        try {
            session.beginTransaction();
            session.save(usuarioPais);
            session.beginTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en Insertar "+e.getMessage());
            session.beginTransaction().rollback();
        }
    }

    public void actualizar(UsuariosPaises usuarioPais) {
        try {
            session.beginTransaction();
            session.update(usuarioPais);
            session.beginTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en Actualizar "+e.getMessage());
            session.beginTransaction().rollback();
        }
    }

    public void eliminar(UsuariosPaises usuarioPais) {
        try {
            session.beginTransaction();
            session.delete(usuarioPais);
            session.beginTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en Eliminar "+e.getMessage());
            session.beginTransaction().rollback();
        }
    }

    public UsuariosPaises buscarPorId(Integer id) {
        return (UsuariosPaises) session.createQuery("FROM UsuariosPaises usuarioPais WHERE usuarioPais.id="+id+" AND usuarioPais.estado="+Boolean.TRUE).iterate().next();
    }

    public List<UsuariosPaises> buscarTodos() {
        return session.createQuery("FROM UsuariosPaises usuarioPais WHERE usuarioPais.estado="+Boolean.TRUE).list();
    }
    public List<UsuariosPaises> buscarPaisesPorIdUsuario(Integer id){
        return session.createQuery("FROM UsuariosPaises usuarioPais WHERE usuarioPais.estado="+Boolean.TRUE+" AND usuarioPais.usuarios.id="+id).list();
    }
}
