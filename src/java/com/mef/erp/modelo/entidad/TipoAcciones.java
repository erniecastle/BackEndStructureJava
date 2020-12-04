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

public enum TipoAcciones {

    PERSONALIZADO(0),
    VENTANA(1),
    SALIRSISTEMA(2),
    SISTEMA(3),
    EXTERNA(4),
    GRUPOREPORTE(5),
    GRUPOCONSULTA(6),
    GRUPOREPORTEPADRE(7),
    GRUPOCONSULTAPADRE(8),
    GRUPOMOVIMIENTONOMINAPADRE(9),
    GRUPOASISTENCIAPADRE(10),
    GRUPOMOVIMIENTONOMINA(11),
    GRUPOASISTENCIA(12),
    SINACCION(13),
    ACCIONMULTIPLE(14),
    REPORTESFIJOS(15),
    CERRARSESSION(16);
    
    private final int tipoAcciones;

    TipoAcciones(int tipoAcciones) {
        this.tipoAcciones = tipoAcciones;
    }

    public int getTipoAcciones() {
        return tipoAcciones;
    }
}
