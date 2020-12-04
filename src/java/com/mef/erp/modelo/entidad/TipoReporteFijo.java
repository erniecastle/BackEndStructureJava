/**
 * @author: Armando Sanchez Fecha de Creación: 08/10/2012 Compañía: MacroPro.
 * Descripción del programa: Enumerador de TipoAccion para identificar
 * diferentes acciones
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

public enum TipoReporteFijo {

    OTROS(0),
    REPORTENOMINA(1),
    REPORTEMOVIMIENTOS(2),
    REPORTEINCAPACIDAD(3),
    RECIBONOMINA(4),
    RESUMENPERCEPDEDUCC(5),
    REPORTEACUMULADOSBASES(6),
    RESUMENCFDI(7),
    EXPORTARACUMULADOSBASES_A_EXCEL(8),
    RESUMENINCIDENCIAS(9),
    RESUMENINCIDENCIAS_A_EXCEL(10),
    RECIBOCFDI(11),
    EXPORTARACUMULADOSDIMM(12),
    REPORTEPTU(13);
    private final int tipoAcciones;

    TipoReporteFijo(int tipoAcciones) {
        this.tipoAcciones = tipoAcciones;
    }

    public int getTipoReporteFijo() {
        return tipoAcciones;
    }

    public static TipoReporteFijo getEnum(int clasificador) {
        if (REPORTENOMINA.getTipoReporteFijo() == clasificador) {
            return REPORTENOMINA;
        } else if (REPORTEMOVIMIENTOS.getTipoReporteFijo() == clasificador) {
            return REPORTEMOVIMIENTOS;
        } else if (REPORTEINCAPACIDAD.getTipoReporteFijo() == clasificador) {
            return REPORTEINCAPACIDAD;
        } else if (RECIBONOMINA.getTipoReporteFijo() == clasificador) {
            return RECIBONOMINA;
        } else if (RESUMENPERCEPDEDUCC.getTipoReporteFijo() == clasificador) {
            return RESUMENPERCEPDEDUCC;
        } else if (REPORTEACUMULADOSBASES.getTipoReporteFijo() == clasificador) {
            return REPORTEACUMULADOSBASES;
        } else if (EXPORTARACUMULADOSBASES_A_EXCEL.getTipoReporteFijo() == clasificador) {
            return EXPORTARACUMULADOSBASES_A_EXCEL;
        } else if (RESUMENCFDI.getTipoReporteFijo() == clasificador) {
            return RESUMENCFDI;
        } else if (RESUMENINCIDENCIAS.getTipoReporteFijo() == clasificador) {
            return RESUMENINCIDENCIAS;
        } else if (RESUMENINCIDENCIAS_A_EXCEL.getTipoReporteFijo() == clasificador) {
            return RESUMENINCIDENCIAS_A_EXCEL;
        } else if (RECIBOCFDI.getTipoReporteFijo() == clasificador) {
            return RECIBOCFDI;
        } else if (EXPORTARACUMULADOSDIMM.getTipoReporteFijo() == clasificador) {
            return EXPORTARACUMULADOSDIMM;
        } else if (REPORTEPTU.getTipoReporteFijo() == clasificador) {
            return REPORTEPTU;
        }else {
            return OTROS;
        }
    }
}
