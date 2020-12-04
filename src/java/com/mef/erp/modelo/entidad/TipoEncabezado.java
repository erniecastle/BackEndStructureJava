/**
 * @author: Ernesto Valenzuela Fecha de Creación: 20/07/2013 Compañía: Exito.
 * Descripción del programa: Enumerador de TipoEncabezado para controlar los
 * Tipos Encabezados de los reportes
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author Ernesto
 */
public enum TipoEncabezado {

    Logo(0),
    NombreEmpresa(1),
    Domicilio(2),
    Calle(3),
    NoExterior(4),
    Nointerior(5),
    Colonia(6),
    RFC(7),
    Telefono(8),
    TituloReporte(9),
    RangoFecha(10),
    MensajeFiltro(11),
    MensajeExtraEncabezado(12),
    MensajeExtraPie(13),
    FechaHoraImpresion(14),
    NoPagina(15),
    Firmas(16),
    Espacio(17);
    private final Integer tipoEncabezado;

    private TipoEncabezado(Integer tipoEncabezado) {
        this.tipoEncabezado = tipoEncabezado;
    }

    public Integer getTipoEncabezado() {
        return tipoEncabezado;
    }
}
