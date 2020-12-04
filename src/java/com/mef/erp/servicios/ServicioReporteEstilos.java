/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ReporteEstilosDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteEstilos;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class ServicioReporteEstilos implements ServicioReporteEstilosIF {

    private ReporteEstilosDAO reporteEstilosDAO;

    public Mensaje agregar(ReporteEstilos entity, String uuidCxnMaestra) {
        return reporteEstilosDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(ReporteEstilos entity, String uuidCxnMaestra) {
        return reporteEstilosDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(ReporteEstilos entity, String uuidCxnMaestra) {
        return reporteEstilosDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje getReporteEstilosAll(String uuidCxnMaestra) {
        return reporteEstilosDAO.getReporteEstilosAll(uuidCxnMaestra);
    }

    public Mensaje getReporteEstilosPorClave(String clave, String uuidCxnMaestra) {
        return reporteEstilosDAO.getReporteEstilosPorClave(clave, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra) {
        return reporteEstilosDAO.consultaPorRangos(inicio, rango, uuidCxnMaestra);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxnMaestra) {
        return reporteEstilosDAO.consultaPorFiltrosReporte(query, campos, valores, uuidCxnMaestra);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra) {
        return reporteEstilosDAO.existeDato(campo, valor, uuidCxnMaestra);
    }

    public Mensaje saveDeleteReporteEstilos(List<ReporteEstilos> entitysCambios, Object[] eliminarReporteEstilos, String uuidCxnMaestra) {
        return reporteEstilosDAO.saveDeleteReporteEstilos(entitysCambios, eliminarReporteEstilos, uuidCxnMaestra);
    }

    public Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra) {
        return reporteEstilosDAO.actualizaListaPorCampos(campoModificar, valoresModificado, camposWhere, valoresWhere, uuidCxnMaestra);
    }

    public ReporteEstilosDAO getReporteEstilosDAO() {
        return reporteEstilosDAO;
    }

    public void setReporteEstilosDAO(ReporteEstilosDAO reporteEstilosDAO) {
        this.reporteEstilosDAO = reporteEstilosDAO;
    }

}
