/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author Daniel
 */
public enum ReporteTipoHoja {
    CARTA(0),
    A4(1),
    LEGAL(2);
    private final int reporteTipoHoja;

    ReporteTipoHoja(int reporteTipoHoja) {
        this.reporteTipoHoja = reporteTipoHoja;
    }

    public int getReporteTipoHoja() {
        return reporteTipoHoja;
    }
}
