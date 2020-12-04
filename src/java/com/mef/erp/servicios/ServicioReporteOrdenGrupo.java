/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ReporteOrdenGrupoDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteOrdenGrupo;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class ServicioReporteOrdenGrupo implements ServicioReporteOrdenGrupoIF {

    private ReporteOrdenGrupoDAO reporteOrdenGrupoDAO;

    public Mensaje agregar(ReporteOrdenGrupo entity, String uuidCxnMaestra) {
        return reporteOrdenGrupoDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(ReporteOrdenGrupo entity, String uuidCxnMaestra) {
        return reporteOrdenGrupoDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(ReporteOrdenGrupo entity, String uuidCxnMaestra) {
        return reporteOrdenGrupoDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje getReporteOrdenGrupoAll( String uuidCxnMaestra) {
        return reporteOrdenGrupoDAO.getReporteOrdenGrupoAll( uuidCxnMaestra);
    }

    public Mensaje getReporteOrdenGrupoPorClave(String clave, String uuidCxnMaestra) {
        return reporteOrdenGrupoDAO.getReporteOrdenGrupoPorClave(clave, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra) {
        return reporteOrdenGrupoDAO.consultaPorRangos(inicio, rango, uuidCxnMaestra);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxnMaestra) {
        return reporteOrdenGrupoDAO.consultaPorFiltrosReporte(query, campos, valores, uuidCxnMaestra);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra) {
        return reporteOrdenGrupoDAO.existeDato(campo, valor, uuidCxnMaestra);
    }

    public Mensaje saveDeleteReporteOrdenGrupo(List<ReporteOrdenGrupo> entitysCambios, Object[] eliminados, String uuidCxnMaestra) {
        return reporteOrdenGrupoDAO.saveDeleteReporteOrdenGrupo(entitysCambios, eliminados, uuidCxnMaestra);
    }

    public Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra) {
        return reporteOrdenGrupoDAO.actualizaListaPorCampos(campoModificar, valoresModificado, camposWhere, valoresWhere, uuidCxnMaestra);
    }

    public ReporteOrdenGrupoDAO getReporteOrdenGrupoDAO() {
        return reporteOrdenGrupoDAO;
    }

    public void setReporteOrdenGrupoDAO(ReporteOrdenGrupoDAO reporteOrdenGrupoDAO) {
        this.reporteOrdenGrupoDAO = reporteOrdenGrupoDAO;
    }

}
