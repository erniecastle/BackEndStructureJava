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
package com.mef.erp.servicios;

import com.mef.erp.modelo.ReporteDinamicoDAO;
import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteDinamico;

/**
 *
 * @author Daniel
 */
public class ServicioReporteDinamico implements ServicioReporteDinamicoIF {

    private ReporteDinamicoDAO reporteDinamicoDAO;

    public Mensaje agregar(ReporteDinamico entity, String uuidCxnMaestra) {
        return reporteDinamicoDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(ReporteDinamico entity, String uuidCxnMaestra) {
        return reporteDinamicoDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(ReporteDinamico entity, String uuidCxnMaestra) {
        return reporteDinamicoDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje getReporteDinamicoAll(String uuidCxnMaestra) {
        return reporteDinamicoDAO.getReporteDinamicoAll(uuidCxnMaestra);
    }

    public Mensaje getReporteDinamicoAllEspecificos(String uuidCxnMaestra) {
        return getReporteDinamicoDAO().getReporteDinamicoAllEspecificos(uuidCxnMaestra);
    }

    public Mensaje getReporteDinamicoPorFuenteYGrupo(String fuenteDatos, Integer idContenedor, String uuidCxnMaestra) {
        return reporteDinamicoDAO.getReporteDinamicoPorFuenteYGrupo(fuenteDatos, idContenedor, uuidCxnMaestra);
    }

    public Mensaje getReporteDinamicoPorContenedor(Integer contenedorID, String uuidCxnMaestra) {
        return reporteDinamicoDAO.getReporteDinamicoPorContenedor(contenedorID, uuidCxnMaestra);
    }

    public Mensaje getReporteDinamicoPorClave(String clave, String uuidCxnMaestra) {//JSA01
        return reporteDinamicoDAO.getReporteDinamicoPorClave(clave, uuidCxnMaestra);
    }

    public Mensaje getReporteDinamicoPorID(Long idReporte, String uuidCxnMaestra) {
        return getReporteDinamicoDAO().getReporteDinamicoPorID(idReporte, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra) {
        return reporteDinamicoDAO.consultaPorRangos(inicio, rango, uuidCxnMaestra);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxnMaestra) {
        return reporteDinamicoDAO.consultaPorFiltrosReporte(query, campos, valores, uuidCxnMaestra);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra) {
        return reporteDinamicoDAO.existeDato(campo, valor, uuidCxnMaestra);
    }

    public Mensaje saveDeleteReporteDinamico(ReporteDinamico entity, Object[] eliminarDatosConsulta, Object[] eliminarDatosIncluir, Object[] eliminarDatosRepOpcGrupo,
            Object[] eliminarDatosOrdenGrupo, Object[] eliminarCamposWhere, Object[] eliminarCamposEncabezados, Object[] eliminarDatosResumen, Object[] eliminarReporteEstilos, Contenedor contenedorGrupoMenu, String uuidCxnMaestra) {
        return reporteDinamicoDAO.saveDeleteReporteDinamico(entity, eliminarDatosConsulta, eliminarDatosIncluir, eliminarDatosRepOpcGrupo, eliminarDatosOrdenGrupo,
                eliminarCamposWhere, eliminarCamposEncabezados, eliminarDatosResumen, eliminarReporteEstilos, contenedorGrupoMenu, uuidCxnMaestra);
    }

    public Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra) {
        return reporteDinamicoDAO.actualizaListaPorCampos(campoModificar, valoresModificado, camposWhere, valoresWhere, uuidCxnMaestra);
    }

    public Mensaje eliminarEspecifico(Long idReporte, String uuidCxnMaestra) {
        return getReporteDinamicoDAO().eliminarEspecifico(idReporte, uuidCxnMaestra);
    }

    public ReporteDinamicoDAO getReporteDinamicoDAO() {
        return reporteDinamicoDAO;
    }

    public void setReporteDinamicoDAO(ReporteDinamicoDAO reporteDinamicoDAO) {
        this.reporteDinamicoDAO = reporteDinamicoDAO;
    }
}
