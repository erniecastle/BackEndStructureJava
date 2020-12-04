/**
 * @author: Ernesto valenzuela Fecha de Creación: 20/07/2013 Compañía: Exito.
 * Descripción del programa: Se creo esta entidad para relacionar otros datos
 * encabezados de los reportes
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.awt.Font;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ernesto
 */
@Entity
@Table(name = "ReporteCamposEncabezado")
public class ReporteCamposEncabezado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String camposCombinar;
    private TipoEncabezado tipoEncabezado;
    private Boolean autocompletar;
    @OneToOne(cascade = CascadeType.ALL)
    private ReporteEstilos reporteEstiloEncabezado;
    private Integer orden;
    private Integer fila;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private ReporteDinamico reporteDinamico;
    @OneToMany(mappedBy = "reporteCamposEncabezado", cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)//JEVC01
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ReporteOtrosDatosEncabezado> reporteOtrosDatosEncabezado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCamposCombinar() {
        return camposCombinar;
    }

    public void setCamposCombinar(String camposCombinar) {
        this.camposCombinar = camposCombinar;
    }

    public TipoEncabezado getTipoEncabezado() {
        return tipoEncabezado;
    }

    public void setTipoEncabezado(TipoEncabezado tipoEncabezado) {
        this.tipoEncabezado = tipoEncabezado;
    }

    public ReporteEstilos getReporteEstiloEncabezado() {
        return reporteEstiloEncabezado;
    }

    public void setReporteEstiloEncabezado(ReporteEstilos reporteEstiloEncabezado) {
        this.reporteEstiloEncabezado = reporteEstiloEncabezado;
    }

    public Boolean getAutocompletar() {
        return autocompletar;
    }

    public void setAutocompletar(Boolean autocompletar) {
        this.autocompletar = autocompletar;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public ReporteDinamico getReporteDinamico() {
        return reporteDinamico;
    }

    public void setReporteDinamico(ReporteDinamico reporteDinamico) {
        this.reporteDinamico = reporteDinamico;
    }

    public List<ReporteOtrosDatosEncabezado> getReporteOtrosDatosEncabezado() {
        return reporteOtrosDatosEncabezado;
    }

    public void setReporteOtrosDatosEncabezado(List<ReporteOtrosDatosEncabezado> reporteOtrosDatosEncabezado) {
        this.reporteOtrosDatosEncabezado = reporteOtrosDatosEncabezado;
    }
}
