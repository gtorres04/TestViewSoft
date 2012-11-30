/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao.impl;

import com.testviewsoft.dao.PaisesDao;
import com.testviewsoft.dao.util.HibernateUtil;
import com.testviewsoft.modelo.Paises;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author GerlinOTS
 */
public class PaisesDaoImpl implements PaisesDao{
    Session session=HibernateUtil.getSessionFactory().openSession();
    public void registrar(Paises pais) {
        try {
            session.beginTransaction();
            session.save(pais);
            session.beginTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Error en Insertar "+e.getMessage());
            session.beginTransaction().rollback();
            session.close();
        }
    }
    
    public void actualizar(Paises pais) {
        try {
            session.beginTransaction();
            session.update(pais);
            session.beginTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Error en Actuaizar "+e.getMessage());
            session.beginTransaction().rollback();
            session.close();
        }
    }
    public void eliminar(Paises pais) {
        try {
            session.beginTransaction();
            session.delete(pais);
            session.beginTransaction().commit();
            pais.setEstado(Boolean.FALSE);
        } catch (Exception e) {
            System.out.println("Error en Eliminar "+e.getMessage());
            session.beginTransaction().rollback();
            pais.setEstado(Boolean.FALSE);
        }
    }

    public Paises buscarPorId(Integer id) {
        return (Paises) session.createQuery("FROM Paises pais WHERE pais.id="+id+" AND pais.estado="+Boolean.TRUE).iterate().next();

    }

    public List<Paises> buscarTodos() {
        return session.createQuery("FROM Paises pais WHERE pais.estado="+Boolean.TRUE).list();
    }
    public void Log(String msn){
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "<<<<[[[["+msn.toUpperCase()+"]]]]>>>>");
    }    
}
