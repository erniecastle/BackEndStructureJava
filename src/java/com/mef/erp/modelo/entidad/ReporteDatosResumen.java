/**
 * @author: Ernesto valenzuela Fecha de Creación: 12/12/2013 Compañía: Exito.
 * Descripción del programa: Se creo esta entidad para relacionar los datos del
 * summary de los reportes
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Ernesto
 */
@Entity
public class ReporteDatosResumen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    private DatosConsulta datosConsulta;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private ReporteDinamico reporteDinamico;
    @OneToOne(cascade = CascadeType.ALL)
    private ReporteEstilos reporteEstiloTitulo;
    @OneToOne(cascade = CascadeType.ALL)
    private ReporteEstilos reporteEstiloDetalle;
    private ReporteTipoOperacion operacion;
    private Integer orden;
    private Integer columna;
    private Integer tamColumna;
    private String datosEspecialesConsulta;
    private Boolean repiteInformacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DatosConsulta getDatosConsulta() {
        return datosConsulta;
    }

    public void setDatosConsulta(DatosConsulta datosConsulta) {
        this.datosConsulta = datosConsulta;
    }

    public ReporteDinamico getReporteDinamico() {
        return reporteDinamico;
    }

    public void setReporteDinamico(ReporteDinamico reporteDinamico) {
        this.reporteDinamico = reporteDinamico;
    }

    public ReporteEstilos getReporteEstiloTitulo() {
        return reporteEstiloTitulo;
    }

    public void setReporteEstiloTitulo(ReporteEstilos reporteEstiloTitulo) {
        this.reporteEstiloTitulo = reporteEstiloTitulo;
    }

    public ReporteEstilos getReporteEstiloDetalle() {
        return reporteEstiloDetalle;
    }

    public void setReporteEstiloDetalle(ReporteEstilos reporteEstiloDetalle) {
        this.reporteEstiloDetalle = reporteEstiloDetalle;
    }

    public ReporteTipoOperacion getOperacion() {
        return operacion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getColumna() {
        return columna;
    }

    public void setColumna(Integer columna) {
        this.columna = columna;
    }

    public void setOperacion(ReporteTipoOperacion operacion) {
        this.operacion = operacion;
    }

    public Integer getTamColumna() {
        return tamColumna;
    }

    public void setTamColumna(Integer tamColumna) {
        this.tamColumna = tamColumna;
    }

    public String getDatosEspecialesConsulta() {
        return datosEspecialesConsulta;
    }

    public void setDatosEspecialesConsulta(String datosEspecialesConsulta) {
        this.datosEspecialesConsulta = datosEspecialesConsulta;
    }

    public Boolean isRepiteInformacion() {
        return repiteInformacion;
    }

    public void setRepiteInformacion(Boolean repiteInformacion) {
        this.repiteInformacion = repiteInformacion;
    }
}
