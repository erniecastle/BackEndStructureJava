/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteEstilos;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface ReporteEstilosDAOIF {

    Mensaje agregar(ReporteEstilos entity, String uuidCxnMaestra);

    Mensaje actualizar(ReporteEstilos entity, String uuidCxnMaestra);

    Mensaje eliminar(ReporteEstilos entity, String uuidCxnMaestra);

    Mensaje getReporteEstilosAll(String uuidCxnMaestra);

    Mensaje getReporteEstilosPorClave(String clave, String uuidCxnMaestra);

    Mensaje saveDeleteReporteEstilos(List<ReporteEstilos> entitysCambios, Object[] eliminados, String uuidCxnMaestra);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    Mensaje consultaPorFiltrosReporte(String query, Object[] campos, Object[] valores, String uuidCxnMaestra);

    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra);
}
