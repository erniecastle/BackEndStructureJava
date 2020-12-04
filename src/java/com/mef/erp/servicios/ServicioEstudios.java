/**
 * @author: Dayane Rocha Fecha de Creación: 19/06/2013 Compañía: Finesoft.
 * Descripción del programa: clase SERVICIO Estudios, para llamados a metodos de
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

import com.mef.erp.modelo.EstudiosDAO;
import com.mef.erp.modelo.entidad.Estudios;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioEstudios implements ServicioEstudiosIF {

    private EstudiosDAO estudiosDAO;
 

    public Mensaje agregar(Estudios entity, String uuidCxn) {
        return getEstudiosDAO().agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Estudios entity, String uuidCxn) {
        return getEstudiosDAO().actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Estudios entity, String uuidCxn) {
        return getEstudiosDAO().eliminar(entity, uuidCxn);
    }

    public Mensaje getEstudioAll(String uuidCxn) {
        return getEstudiosDAO().getEstudioAll(uuidCxn);
    }

    public Mensaje getEstudioPorClave(String clave, String uuidCxn) {
        return getEstudiosDAO().getEstudioPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltrosEstudio(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return getEstudiosDAO().consultaPorFiltrosEstudio(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangosEstudio(Integer inicio, Integer rango, String uuidCxn) {
        return getEstudiosDAO().consultaPorRangosEstudio(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return getEstudiosDAO().existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteEstudios(List<Estudios> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return getEstudiosDAO().saveDeleteEstudios(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public EstudiosDAO getEstudiosDAO() {
        return estudiosDAO;
    }

    public void setEstudiosDAO(EstudiosDAO estudiosDAO) {
        this.estudiosDAO = estudiosDAO;
    }
}
