/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteOrdenGrupo;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface ServicioReporteOrdenGrupoIF {

    /*ReporteOrdenGrupo*/
    Mensaje agregar(ReporteOrdenGrupo entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje actualizar(ReporteOrdenGrupo entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje eliminar(ReporteOrdenGrupo entity, String uuidCxnMaestra);

    /*List<ReporteOrdenGrupo>*/
    Mensaje getReporteOrdenGrupoAll(String uuidCxnMaestra);

    /*ReporteOrdenGrupo*/
    Mensaje getReporteOrdenGrupoPorClave(String clave, String uuidCxnMaestra);

    /*List<ReporteOrdenGrupo>*/
    Mensaje saveDeleteReporteOrdenGrupo(List<ReporteOrdenGrupo> entitysCambios, Object[] eliminados, String uuidCxnMaestra);

    /*List<ReporteOrdenGrupo>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    /*List<ReporteOrdenGrupo>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxnMaestra);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    /*int*/
    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra);
}
