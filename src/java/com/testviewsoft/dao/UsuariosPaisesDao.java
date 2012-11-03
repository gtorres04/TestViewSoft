/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao;

import com.testviewsoft.modelo.UsuariosPaises;
import java.util.List;

/**
 *
 * @author GerlinOTS
 */
public interface UsuariosPaisesDao {
    public void registrar(UsuariosPaises usuariosPaises);
    public void actualizar(UsuariosPaises usuariosPaises);
    public void eliminar(UsuariosPaises usuariosPaises);
    public UsuariosPaises buscarPorId(Integer id);
    public List<UsuariosPaises> buscarTodos();
}
