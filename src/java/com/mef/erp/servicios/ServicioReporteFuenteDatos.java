/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ReporteFuenteDatosDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteFuenteDatos;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class ServicioReporteFuenteDatos implements ServicioReporteFuenteDatosIF {

    private ReporteFuenteDatosDAO reporteFuenteDatosDAO;

    public Mensaje agregar(ReporteFuenteDatos entity, String uuidCxnMaestra) {
        return reporteFuenteDatosDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(ReporteFuenteDatos entity, String uuidCxnMaestra) {
        return reporteFuenteDatosDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(ReporteFuenteDatos entity, String uuidCxnMaestra) {
        return reporteFuenteDatosDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje getReporteFuenteDatosAll(String uuidCxnMaestra) {
        return reporteFuenteDatosDAO.getReporteFuenteDatosAll(uuidCxnMaestra);
    }

    public Mensaje getReporteFuenteDatosPorClave(String clave, String uuidCxnMaestra) {
        return reporteFuenteDatosDAO.getReporteFuenteDatosPorClave(clave, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra) {
        return reporteFuenteDatosDAO.consultaPorRangos(inicio, rango, uuidCxnMaestra);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxnMaestra) {
        return reporteFuenteDatosDAO.consultaPorFiltrosFuente(query, campos, valores, uuidCxnMaestra);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra) {
        return reporteFuenteDatosDAO.existeDato(campo, valor, uuidCxnMaestra);
    }

    public Mensaje saveDeleteReporteFuenteDatos(List<ReporteFuenteDatos> entitysCambios, Object[] eliminados, String uuidCxnMaestra) {
        return reporteFuenteDatosDAO.saveDeleteReporteFuenteDatos(entitysCambios, eliminados, uuidCxnMaestra);
    }

    public Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra) {
        return reporteFuenteDatosDAO.actualizaListaPorCampos(campoModificar, valoresModificado, camposWhere, valoresWhere, uuidCxnMaestra);
    }

    public ReporteFuenteDatosDAO getReporteFuenteDatosDAO() {
        return reporteFuenteDatosDAO;
    }

    public void setReporteFuenteDatosDAO(ReporteFuenteDatosDAO reporteFuenteDatosDAO) {
        this.reporteFuenteDatosDAO = reporteFuenteDatosDAO;
    }

}
