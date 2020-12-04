/**
 * @author: Dayane Rocha
 * Fecha de Creación: 20/06/2013
 * Compañía: Finesoft.
 * Descripción del programa: Interface para servicio Cursos
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: 
 * Autor: 
 * Fecha: 
 * Descripción: 
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Cursos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Dayane
 */
public interface ServicioCursosIF {

    /*Cursos*/
    Mensaje agregar(Cursos entity,String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Cursos entity,String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Cursos entity,String uuidCxn);

    /*List<Cursos>*/
    Mensaje getCursoAll(String uuidCxn);

    /*Cursos*/
    Mensaje getCursoPorClave(String clave,String uuidCxn);

    /*List<Cursos>*/
    public Mensaje consultaPorFiltrosCurso(String query, Object[] campos, Object[] valores,String uuidCxn);

    /*List<Cursos>*/
    public Mensaje consultaPorRangosCurso(Integer inicio, Integer rango,String uuidCxn);

    /*Boolean*/
    public Mensaje existeDato(String campo, Object valor,String uuidCxn);

    /*List<Cursos>*/
    public Mensaje saveDeleteCursos(List<Cursos> entitysCambios, Object[] clavesDelete, int rango,String uuidCxn);
    
   
}
