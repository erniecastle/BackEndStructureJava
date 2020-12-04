/**
 * @author: Dayane Rocha Fecha de Creación: 20/06/2013 Compañía: Finesoft.
 * Descripción del programa: clase SERVICIO Cursos, para llamados a metodos de
 * objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CursosDAO;
import com.mef.erp.modelo.entidad.Cursos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Dayane
 */
public class ServicioCursos implements ServicioCursosIF {

    private CursosDAO cursosDAO;


    public Mensaje agregar(Cursos entity, String uuidCxn) {
        return getCursosDAO().agregar(entity,uuidCxn);
    }

    public Mensaje actualizar(Cursos entity, String uuidCxn) {
        return getCursosDAO().actualizar(entity,uuidCxn);
    }

    public Mensaje eliminar(Cursos entity, String uuidCxn) {
        return getCursosDAO().eliminar(entity,uuidCxn);
    }

    public Mensaje getCursoAll(String uuidCxn) {
        return getCursosDAO().getCursoAll(uuidCxn);
    }

    public Mensaje getCursoPorClave(String clave, String uuidCxn) {

        return getCursosDAO().getCursoPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltrosCurso(String query, Object[] campos, Object[] valores, String uuidCxn) {

        return getCursosDAO().consultaPorFiltrosCurso(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangosCurso(Integer inicio, Integer rango, String uuidCxn) {
    
        return getCursosDAO().consultaPorRangosCurso(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
    
        return getCursosDAO().existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteCursos(List<Cursos> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
       
        return getCursosDAO().saveDeleteCursos(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public CursosDAO getCursosDAO() {
        return cursosDAO;
    }

    public void setCursosDAO(CursosDAO cursosDAO) {
        this.cursosDAO = cursosDAO;
    }

}
