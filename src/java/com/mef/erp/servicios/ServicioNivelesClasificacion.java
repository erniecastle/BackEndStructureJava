/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.NivelesClasificacionDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.NivelesClasificacion;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioNivelesClasificacion implements ServicioNivelesClasificacionIF {

    private NivelesClasificacionDAO nivelesClasificacionDAO;

    public Mensaje agregar(NivelesClasificacion entity, String uuidCxn) {

        return nivelesClasificacionDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(NivelesClasificacion entity, String uuidCxn) {

        return getNivelesClasificacionDAO().actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(NivelesClasificacion entity, String uuidCxn) {

        return getNivelesClasificacionDAO().eliminar(entity, uuidCxn);
    }

    public Mensaje getNivelesClasificacionAll(String uuidCxn) {

        return getNivelesClasificacionDAO().getNivelesClasificacionAll(uuidCxn);
    }

    public Mensaje getNivelesClasificacionPorClave(String clave, String uuidCxn) {

        return getNivelesClasificacionDAO().getNivelesClasificacionPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {

        return getNivelesClasificacionDAO().consultaPorFiltrosNivel(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {

        return getNivelesClasificacionDAO().consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {

        return getNivelesClasificacionDAO().existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteNivelesClasificacion(List<NivelesClasificacion> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {

        return getNivelesClasificacionDAO().saveDeleteNivelesClasificacion(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    /**
     * @return the nivelesClasificacionDAO
     */
    public NivelesClasificacionDAO getNivelesClasificacionDAO() {

        return nivelesClasificacionDAO;
    }

    /**
     * @param nivelesClasificacionDAO the nivelesClasificacionDAO to set
     */
    public void setNivelesClasificacionDAO(NivelesClasificacionDAO nivelesClasificacionDAO) {
        this.nivelesClasificacionDAO = nivelesClasificacionDAO;
    }

}
