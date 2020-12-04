/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ReporteDatosIncluirDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ReporteDatosIncluir;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class ServicioReporteDatosIncluir implements ServicioReporteDatosIncluirIF {

    private ReporteDatosIncluirDAO reporteDatosIncluirDAO;

    public Mensaje agregar(ReporteDatosIncluir entity, String uuidCxnMaestra) {
        return reporteDatosIncluirDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(ReporteDatosIncluir entity, String uuidCxnMaestra) {
        return reporteDatosIncluirDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(ReporteDatosIncluir entity, String uuidCxnMaestra) {
        return reporteDatosIncluirDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje getReporteDatosIncluirAll(String uuidCxnMaestra) {
        return reporteDatosIncluirDAO.getReporteDatosIncluirAll(uuidCxnMaestra);
    }

    public Mensaje getReporteDatosIncluirPorClave(String clave, String uuidCxnMaestra) {
        return reporteDatosIncluirDAO.getReporteDatosIncluirPorClave(clave, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra) {
        return reporteDatosIncluirDAO.consultaPorRangos(inicio, rango, uuidCxnMaestra);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxnMaestra) {
        return reporteDatosIncluirDAO.consultaPorFiltrosReporte(query, campos, valores, uuidCxnMaestra);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra) {
        return reporteDatosIncluirDAO.existeDato(campo, valor, uuidCxnMaestra);
    }

    public Mensaje saveDeleteReporteDatosIncluir(List<ReporteDatosIncluir> entitysCambios, Object[] eliminados, String uuidCxnMaestra) {
        return reporteDatosIncluirDAO.saveDeleteReporteDatosIncluir(entitysCambios, eliminados, uuidCxnMaestra);
    }

    public Mensaje actualizaListaPorCampos(String[] campoModificar, Object[] valoresModificado, String[] camposWhere, Object[] valoresWhere, String uuidCxnMaestra) {
        return reporteDatosIncluirDAO.actualizaListaPorCampos(campoModificar, valoresModificado, camposWhere, valoresWhere, uuidCxnMaestra);
    }

    public ReporteDatosIncluirDAO getReporteDatosIncluirDAO() {
        return reporteDatosIncluirDAO;
    }

    public void setReporteDatosIncluirDAO(ReporteDatosIncluirDAO reporteDatosIncluirDAO) {
        this.reporteDatosIncluirDAO = reporteDatosIncluirDAO;
    }

}
