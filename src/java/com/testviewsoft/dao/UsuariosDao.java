/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao;

import com.testviewsoft.modelo.Usuarios;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author GerlinOTS
 */
public interface UsuariosDao {
    public void registrar(Usuarios usuario);
    public void actualizar(Usuarios usuario);
    public void eliminar(Usuarios usuario);
    public Usuarios buscarPorId(Integer id);
    public List<Usuarios> buscarTodos();
    public Usuarios buscarPorReferenciaIdentificacionTipoDocumento(String referenciaIdentificacion);
}
