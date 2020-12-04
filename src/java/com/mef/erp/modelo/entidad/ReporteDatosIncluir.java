/**
 * @author: Daniel Fecha de Creacion: --/--/2012 Compañia: Finesoft. Descripcion
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 23/11/2012 Descripción: Se cambio el
 * campo de ReporteDatos por DatosConsulta
 * -----------------------------------------------------------------------------
 * Clave: JSA02 Autor: ARMANDO Fecha: 21/02/2013 Descripción: Se agrego el campo
 * datosEspecialesConsulta para guardar los datos requeridos para la consulta.
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
import javax.persistence.Table;

/**
 *
 * @author Ernesto
 */
@Entity
@Table(name = "ReporteDatosIncluir")
public class ReporteDatosIncluir implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    private DatosConsulta datosConsulta;//JSA01
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})//JEVC01
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
