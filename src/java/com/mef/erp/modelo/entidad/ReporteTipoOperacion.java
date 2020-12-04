/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author xp
 */
public enum ReporteTipoOperacion {

    SUMA(0),
    MAXIMO(1),
    MINIMO(2),
    PROMEDIO(3),
    CANTIDAD(4);
    private final int reporteTipoOperacion;

    ReporteTipoOperacion(int reporteTipoOperacion) {
        this.reporteTipoOperacion = reporteTipoOperacion;
    }

    public int getReporteTipoOperacion() {
        return reporteTipoOperacion;
    }
}
