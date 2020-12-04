/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.NivelesClasificacion;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface NivelesClasificacionDAOIF extends GenericDAO<NivelesClasificacion, Long> {

    public Mensaje agregar(NivelesClasificacion entity, String uuidCxn);

    public Mensaje actualizar(NivelesClasificacion entity, String uuidCxn);

    public Mensaje eliminar(NivelesClasificacion entity, String uuidCxn);

    public Mensaje getNivelesClasificacionAll(String uuidCxn);

    public Mensaje getNivelesClasificacionPorClave(String clave, String uuidCxn);

    Mensaje consultaPorFiltrosNivel(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje saveDeleteNivelesClasificacion(List<NivelesClasificacion> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
