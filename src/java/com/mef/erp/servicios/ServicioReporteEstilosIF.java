/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteEstilos;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface ServicioReporteEstilosIF {

    /*ReporteEstilos*/
    Mensaje agregar(ReporteEstilos entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje actualizar(ReporteEstilos entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje eliminar(ReporteEstilos entity, String uuidCxnMaestra);

    /*List<ReporteEstilos>*/
    Mensaje getReporteEstilosAll(String uuidCxnMaestra);

    /*ReporteEstilos*/
    Mensaje getReporteEstilosPorClave(String clave, String uuidCxnMaestra);

    /*List<ReporteEstilos>*/
    Mensaje saveDeleteReporteEstilos(List<ReporteEstilos> entitysCambios, Object[] eliminarReporteEstilos, String uuidCxnMaestra);

    /*List<ReporteEstilos>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    /*List<ReporteEstilos>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxnMaestra);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    /*int*/
    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra);
}
