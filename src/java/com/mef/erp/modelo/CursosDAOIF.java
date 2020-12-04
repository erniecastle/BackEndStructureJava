/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Cursos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Dayane
 */
public interface CursosDAOIF extends GenericDAO<Cursos, String> {
    
    Mensaje agregar(Cursos entity,String uuidCxn);

    public Mensaje actualizar(Cursos entity,String uuidCxn);

    public Mensaje eliminar(Cursos entity,String uuidCxn);

    Mensaje getCursoAll(String uuidCxn);

    Mensaje getCursoPorClave(String clave,String uuidCxn);

    public Mensaje consultaPorFiltrosCurso(String query, Object[] campos, Object[] valores,String uuidCxn);

    public Mensaje consultaPorRangosCurso(Integer inicio, Integer rango,String uuidCxn);

    public Mensaje existeDato(String campo, Object valor,String uuidCxn);

    public Mensaje saveDeleteCursos(List<Cursos> entitysCambios, Object[] clavesDelete, int rango,String uuidCxn);
    
}
