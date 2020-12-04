/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.NivelesClasificacion;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioNivelesClasificacionIF {

    /*NivelesClasificacion*/
    public Mensaje agregar(NivelesClasificacion entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(NivelesClasificacion entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(NivelesClasificacion entity, String uuidCxn);

    /*List<NivelesClasificacion>*/
    public Mensaje getNivelesClasificacionAll(String uuidCxn);

    /*NivelesClasificacion*/
    public Mensaje getNivelesClasificacionPorClave(String clave, String uuidCxn);

    /*List<NivelesClasificacion>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    /*List<NivelesClasificacion>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);
	
	/*List<NivelesClasificacion>*/
    Mensaje saveDeleteNivelesClasificacion(List<NivelesClasificacion> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);


}
