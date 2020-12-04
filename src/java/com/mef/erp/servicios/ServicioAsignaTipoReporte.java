/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.AsignaTipoReporteDAO;
import com.mef.erp.modelo.entidad.AsignaTipoReporte;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoReporte;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioAsignaTipoReporte implements ServicioAsignaTipoReporteIF {

    private AsignaTipoReporteDAO asignaTipoReporteDAO;

    public Mensaje agregar(AsignaTipoReporte entity, String uuidCxn) {
        return asignaTipoReporteDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(AsignaTipoReporte entity, String uuidCxn) {
        return asignaTipoReporteDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(AsignaTipoReporte entity, String uuidCxn) {
        return asignaTipoReporteDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getAsignaTipoReporteAll(String uuidCxn) {
        return asignaTipoReporteDAO.getAsignaTipoReporteAll(uuidCxn);
    }

    public Mensaje getAsignaPorTipoReporteDinamico(String claveReporteDinamico, String uuidCxn) {
        return asignaTipoReporteDAO.getAsignaPorTipoReporteDinamico(claveReporteDinamico, uuidCxn);
    }

    public Mensaje getAsignaPorTipoReporte(TipoReporte tipoReporte, String uuidCxn) {
        return getAsignaTipoReporteDAO().getAsignaPorTipoReporte(tipoReporte, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return asignaTipoReporteDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteAsignaTipoReporte(List<AsignaTipoReporte> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return asignaTipoReporteDAO.saveDeleteAsignaTipoReporte(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public AsignaTipoReporteDAO getAsignaTipoReporteDAO() {
        return asignaTipoReporteDAO;
    }

    public void setAsignaTipoReporteDAO(AsignaTipoReporteDAO asignaTipoReporteDAO) {
        this.asignaTipoReporteDAO = asignaTipoReporteDAO;
    }
}
