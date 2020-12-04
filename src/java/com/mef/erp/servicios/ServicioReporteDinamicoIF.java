/**
 * @author: Daniel Fecha de Creación: 00/00/0000 Compañía: FINESOFT Descripción
 * del programa:Interface
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteDinamico;

/**
 *
 * @author Daniel
 */
public interface ServicioReporteDinamicoIF {

    /*ReporteDinamico*/
    Mensaje agregar(ReporteDinamico entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje actualizar(ReporteDinamico entity, String uuidCxnMaestra);

    /*boolean*/
    Mensaje eliminar(ReporteDinamico entity, String uuidCxnMaestra);

    /*List<ReporteDinamico>*/
    Mensaje getReporteDinamicoAll(String uuidCxnMaestra);

    /*List<Object>*/
    public Mensaje getReporteDinamicoAllEspecificos(String uuidCxnMaestra);

    /*List<Object>*/
    Mensaje getReporteDinamicoPorFuenteYGrupo(String fuenteDatos, Integer idContenedor, String uuidCxnMaestra);

    /*List<ReporteDinamico>*/
    Mensaje getReporteDinamicoPorContenedor(Integer contenedorID, String uuidCxnMaestra);

    /*ReporteDinamico*/
    Mensaje getReporteDinamicoPorClave(String clave, String uuidCxnMaestra);

    /*ReporteDinamico*/
    public Mensaje getReporteDinamicoPorID(Long idReporte, String uuidCxnMaestra);

    /*ReporteDinamico*/
    Mensaje saveDeleteReporteDinamico(ReporteDinamico entity, Object[] eliminarDatosConsulta, Object[] eliminarDatosIncluir, Object[] eliminarDatosRepOpcGrupo,
            Object[] eliminarDatosOrdenGrupo, Object[] eliminarCamposWhere, Object[] eliminarCamposEncabezados, Object[] eliminarDatosResumen, Object[] eliminarReporteEstilos, Contenedor contenedorGrupoMenu, String uuidCxnMaestra);

    /*List<ReporteDinamico>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    /*List<ReporteDinamico>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxnMaestra);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    /*int*/
    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra);

    /*ReporteDinamico*/
    public Mensaje eliminarEspecifico(Long idReporte, String uuidCxnMaestra);
}
