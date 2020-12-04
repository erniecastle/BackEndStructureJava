/**
 * @author: Dayane Rocha Fecha de Creación: 19/06/2013 Compañía: Finesoft.
 * Descripción del programa: Interface para servicio Estudios
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Estudios;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioEstudiosIF {

    /*Estudios*/
    Mensaje agregar(Estudios entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Estudios entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Estudios entity, String uuidCxn);

    /*List<Estudios>*/
    Mensaje getEstudioAll(String uuidCxn);

    /*Estudios*/
    Mensaje getEstudioPorClave(String clave, String uuidCxn);

    /*List<Estudios>*/
    public Mensaje consultaPorFiltrosEstudio(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<Estudios>*/
    public Mensaje consultaPorRangosEstudio(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    public Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<Estudios>*/
    public Mensaje saveDeleteEstudios(List<Estudios> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    
}
