/**
 * @author: Daniel Fecha de Creacion: --/--/----, Companía: Finesoft.
 * Descripcion del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Armando Fecha: 06/09/2013 Descripción:Se agrego el
 * parametro de razones sociales.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteDinamico;

/**
 *
 * @author Daniel
 */
public interface ReporteDinamicoDAOIF {

    Mensaje agregar(ReporteDinamico entity, String uuidCxnMaestra);

    Mensaje actualizar(ReporteDinamico entity, String uuidCxnMaestra);

    Mensaje eliminar(ReporteDinamico entity, String uuidCxnMaestra);

    Mensaje getReporteDinamicoAll(String uuidCxnMaestra);

    Mensaje getReporteDinamicoAllEspecificos(String uuidCxnMaestra);

    Mensaje getReporteDinamicoPorFuenteYGrupo(String fuenteDatos, Integer idContenedor, String uuidCxnMaestra);

    Mensaje getReporteDinamicoPorContenedor(Integer contenedorID, String uuidCxnMaestra);

    Mensaje getReporteDinamicoPorClave(String clave, String uuidCxnMaestra);

    public Mensaje getReporteDinamicoPorID(Long idReporte, String uuidCxnMaestra);

    Mensaje saveDeleteReporteDinamico(ReporteDinamico entity, Object[] eliminarDatosConsulta, Object[] eliminarDatosIncluir, Object[] eliminarDatosRepOpcGrupo,
            Object[] eliminarDatosOrdenGrupo, Object[] eliminarCamposWhere, Object[] eliminarCamposEncabezados, Object[] eliminarDatosResumen, Object[] eliminarReporteEstilos, Contenedor contenedorGrupoMenu, String uuidCxnMaestra);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra);

    Mensaje consultaPorFiltrosReporte(String query, Object[] campos, Object[] valores, String uuidCxnMaestra);

    Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra);

    Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra);

    public Mensaje eliminarEspecifico(Long idReporte, String uuidCxnMaestra);
}
