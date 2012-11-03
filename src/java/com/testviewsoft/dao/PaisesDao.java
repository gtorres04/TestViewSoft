/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao;

import com.testviewsoft.modelo.Paises;
import java.util.List;

/**
 *
 * @author GerlinOTS
 */
public interface PaisesDao {
    public void registrar(Paises paises);
    public void actualizar(Paises paises);
    public void eliminar(Paises paises);
    public Paises buscarPorId(Integer id);
    public List<Paises> buscarTodos();
}
