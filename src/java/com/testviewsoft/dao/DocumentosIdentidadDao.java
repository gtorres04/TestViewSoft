/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.testviewsoft.dao;

import com.testviewsoft.modelo.DocumentosIdentidad;
import java.util.List;

/**
 *
 * @author GerlinOTS
 */
public interface DocumentosIdentidadDao {
    
    public void registrar(DocumentosIdentidad documentoIdentidad);
    public void actualizar(DocumentosIdentidad documentoIdentidad);
    public void eliminar(DocumentosIdentidad documentoIdentidad);
    public DocumentosIdentidad buscarPorId(Integer id);
    public List<DocumentosIdentidad> buscarTodosEstadoTRUE();
}
