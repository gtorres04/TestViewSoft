/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.DocumentosIdentidadDao;
import com.testviewsoft.dao.util.HibernateUtil;
import com.testviewsoft.modelo.DocumentosIdentidad;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author GerlinOTS
 */
public class DocumentosIdentidadDaoImpl implements DocumentosIdentidadDao{
    Session session=HibernateUtil.getSessionFactory().openSession();
    public void registrar(DocumentosIdentidad documentoIdentidad) {
        try {
            session.beginTransaction();
            session.save(documentoIdentidad);
            session.beginTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en Insertar "+e.getMessage());
            session.beginTransaction().rollback();
        }
    }
    
    public void actualizar(DocumentosIdentidad documentoIdentidad) {
        try {
            session.beginTransaction();
            session.update(documentoIdentidad);
            session.beginTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en Actuaizar "+e.getMessage());
            session.beginTransaction().rollback();
        }
    }

    public void eliminar(DocumentosIdentidad documentoIdentidad) {
        try {
            session.beginTransaction();
            session.delete(documentoIdentidad);
            session.beginTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en Eliminar "+e.getMessage());
            session.beginTransaction().rollback();
        }
    }

    public DocumentosIdentidad buscarPorId(Integer id) {
        return (DocumentosIdentidad) session.createQuery("FROM DocumentosIdentidad documentoIdentidad WHERE documentoIdentidad.id="+id+" AND documentoIdentidad.estado="+Boolean.TRUE).iterate().next();
    }

    public List<DocumentosIdentidad> buscarTodos() {
        return session.createQuery("FROM DocumentosIdentidad documentoIdentidad WHERE documentoIdentidad.estado="+Boolean.TRUE).list();
    }
    
}
