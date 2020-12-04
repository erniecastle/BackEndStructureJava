/**
 * @author: Daniel Fecha de Creacion: --/--/2012 Compañía: Finesoft. Descripción
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 23/11/2012 Descripción: Se cambio el
 * campo de ReporteDatos por DatosConsulta
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

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
 * @author daniel
 */
@Entity
@Table(name = "ReporteOrdenGrupo")
public class ReporteOrdenGrupo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private DatosConsulta datosConsulta;//JSA01
    @ManyToOne(cascade = CascadeType.REFRESH)
    private ReporteDinamico reporteDinamico;//JECVC01
    @OneToOne(cascade = CascadeType.ALL)//JECVC02
    private ReporteEstilos reporteEstiloGrupo;
    @OneToOne(cascade = CascadeType.ALL)//JECVC02
    private ReporteEstilos reporteEstiloEncabezado;
    @OneToOne(cascade = CascadeType.ALL)//JECVC02
    private ReporteEstilos reporteEstiloPie;
    private Boolean agrupar;
    private Boolean incluirEncabezado;
    private Boolean incluirPie;
    private Integer orden;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "reporteOrdenGrupo", cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<ReporteOpcionGrupos> reporteOpcionGrupos;

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

    public ReporteEstilos getReporteEstiloGrupo() {
        return reporteEstiloGrupo;
    }

    public void setReporteEstiloGrupo(ReporteEstilos reporteEstiloGrupo) {
        this.reporteEstiloGrupo = reporteEstiloGrupo;
    }

    public ReporteEstilos getReporteEstiloEncabezado() {
        return reporteEstiloEncabezado;
    }

    public void setReporteEstiloEncabezado(ReporteEstilos reporteEstiloEncabezado) {
        this.reporteEstiloEncabezado = reporteEstiloEncabezado;
    }

    public ReporteEstilos getReporteEstiloPie() {
        return reporteEstiloPie;
    }

    public void setReporteEstiloPie(ReporteEstilos reporteEstiloPie) {
        this.reporteEstiloPie = reporteEstiloPie;
    }

    public Boolean getAgrupar() {
        return agrupar;
    }

    public void setAgrupar(Boolean agrupar) {
        this.agrupar = agrupar;
    }

    public Boolean getIncluirEncabezado() {
        return incluirEncabezado;
    }

    public void setIncluirEncabezado(Boolean incluirEncabezado) {
        this.incluirEncabezado = incluirEncabezado;
    }

    public Boolean getIncluirPie() {
        return incluirPie;
    }

    public void setIncluirPie(Boolean incluirPie) {
        this.incluirPie = incluirPie;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public List<ReporteOpcionGrupos> getReporteOpcionGrupos() {
        return reporteOpcionGrupos;
    }

    public void setReporteOpcionGrupos(List<ReporteOpcionGrupos> reporteOpcionGrupos) {
        this.reporteOpcionGrupos = reporteOpcionGrupos;
    }

}
