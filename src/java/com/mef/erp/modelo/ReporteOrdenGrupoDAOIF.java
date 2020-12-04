/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteOrdenGrupo;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface ReporteOrdenGrupoDAOIF {

    Mensaje agregar(ReporteOrdenGrupo entity, String uuidCxnMaestra);

    Mensaje actualizar(ReporteOrdenGrupo entity, String uuidCxnMaestra);

    Mensaje eliminar(ReporteOrdenGrupo entity, String uuidCxnMaestra);

    Mensaje getReporteOrdenGrupoAll(String uuidCxnMaestra);

    Mensaje getReporteOrdenGrupoPorClave(String clave, String uuidCxnMaestra);

    Mensaje saveDeleteReporteOrdenGrupo(List<ReporteOrdenGrupo> entitysCambios, Object[] eliminados, String uuidCxnMaestra);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    Mensaje consultaPorFiltrosReporte(String query, Object[] campos, Object[] valores, String uuidCxnMaestra);

    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra);
}
