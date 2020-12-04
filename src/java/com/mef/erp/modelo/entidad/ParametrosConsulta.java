/**
 * @author: Victor Lopez Fecha de Creación: 28/09/2012 Compañía: Macropro.
 * Descripción del programa: Entidad de ParametrosConsulta con Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 06/12/2012 Descripción: se cambio el
 * fuentesDatos por la entidad de ReporteFuenteDatos
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ParametrosConsulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String nombreAbreviado;
    @ManyToOne
    private ReporteFuenteDatos reporteFuenteDatos;//JSA01
    @OneToOne(cascade = CascadeType.ALL)
    private Contenedor contenedorGrupo;
    @Column(length = 1000)
    private String camposMostrar;
    private String sizeColumnas;
    @Column(length = 500)
    private String camposAgrupar;
    @Column(length = 500)
    private String camposTotalizar;
    @Column(length = 500)
    private String totalizarGrupos;
    @Column(length = 500)
    private String autoCompletarGrupos;
    @Column(length = 500)
    private String addSeparadorGrupos;
    @Column(length = 500)
    private String tipoFormatoTotales;
    @Column(length = 500)
    private String tipoFormatoCamposMostrar;
    private boolean totalGlobal;
    private boolean modoVisualizarTabla;
    @Column(length = 2000)
    private String tituloCamposVisualizar;
    @Column(length = 1000)
    private String tituloGrupoVisualizar;
    @Column(length = 1000)
    private String tituloTotalVisualizar;
    @Column(length = 500)
    private String datosEspecialesConsulta;
    @Column(length = 500)
    private String camposWhereExtras;
    @Column(length = 5)
    private String tipoOrdenado;
    private Boolean usaFiltroCorrida;

    public ParametrosConsulta() {
    }

    public ParametrosConsulta(Long id, String nombre, String nombreAbreviado, String reporteFuenteDatosNombre, String contenedorGrupoNombre) {
        this.id = id;
        this.nombre = nombre;
        this.nombreAbreviado = nombreAbreviado;
        ReporteFuenteDatos fuenteDatos = new ReporteFuenteDatos();
        fuenteDatos.setNombre(reporteFuenteDatosNombre);
        this.reporteFuenteDatos = fuenteDatos;
        Contenedor c = new Contenedor();
        c.setNombre(contenedorGrupoNombre);
        this.contenedorGrupo = c;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReporteFuenteDatos getReporteFuenteDatos() {
        return reporteFuenteDatos;
    }

    public void setReporteFuenteDatos(ReporteFuenteDatos reporteFuenteDatos) {
        this.reporteFuenteDatos = reporteFuenteDatos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreAbreviado() {
        return nombreAbreviado;
    }

    public void setNombreAbreviado(String nombreAbreviado) {
        this.nombreAbreviado = nombreAbreviado;
    }

    public String getCamposAgrupar() {
        return camposAgrupar;
    }

    public void setCamposAgrupar(String camposAgrupar) {
        this.camposAgrupar = camposAgrupar;
    }

    public String getCamposMostrar() {
        return camposMostrar;
    }

    public void setCamposMostrar(String camposMostrar) {
        this.camposMostrar = camposMostrar;
    }

    public String getCamposTotalizar() {
        return camposTotalizar;
    }

    public void setCamposTotalizar(String camposTotalizar) {
        this.camposTotalizar = camposTotalizar;
    }

    public String getSizeColumnas() {
        return sizeColumnas;
    }

    public void setSizeColumnas(String sizeColumnas) {
        this.sizeColumnas = sizeColumnas;
    }

    public String getTotalizarGrupos() {
        return totalizarGrupos;
    }

    public void setTotalizarGrupos(String totalizarGrupos) {
        this.totalizarGrupos = totalizarGrupos;
    }

    public boolean isTotalGlobal() {
        return totalGlobal;
    }

    public void setTotalGlobal(boolean totalGlobal) {
        this.totalGlobal = totalGlobal;
    }

    public Contenedor getContenedorGrupo() {
        return contenedorGrupo;
    }

    public void setContenedorGrupo(Contenedor contenedorGrupo) {
        this.contenedorGrupo = contenedorGrupo;
    }

    public String getAutoCompletarGrupos() {
        return autoCompletarGrupos;
    }

    public void setAutoCompletarGrupos(String autoCompletarGrupos) {
        this.autoCompletarGrupos = autoCompletarGrupos;
    }

    public String getAddSeparadorGrupos() {
        return addSeparadorGrupos;
    }

    public void setAddSeparadorGrupos(String addSeparadorGrupos) {
        this.addSeparadorGrupos = addSeparadorGrupos;
    }

    public String getTipoFormatoTotales() {
        return tipoFormatoTotales;
    }

    public void setTipoFormatoTotales(String tipoFormatoTotales) {
        this.tipoFormatoTotales = tipoFormatoTotales;
    }

    public boolean isModoVisualizarTabla() {
        return modoVisualizarTabla;
    }

    public void setModoVisualizarTabla(boolean modoVisualizarTabla) {
        this.modoVisualizarTabla = modoVisualizarTabla;
    }

    public String getTipoFormatoCamposMostrar() {
        return tipoFormatoCamposMostrar;
    }

    public void setTipoFormatoCamposMostrar(String tipoFormatoCamposMostrar) {
        this.tipoFormatoCamposMostrar = tipoFormatoCamposMostrar;
    }

    public String getTituloCamposVisualizar() {
        return tituloCamposVisualizar;
    }

    public void setTituloCamposVisualizar(String tituloCamposVisualizar) {
        this.tituloCamposVisualizar = tituloCamposVisualizar;
    }

    public String getTituloGrupoVisualizar() {
        return tituloGrupoVisualizar;
    }

    public void setTituloGrupoVisualizar(String tituloGrupoVisualizar) {
        this.tituloGrupoVisualizar = tituloGrupoVisualizar;
    }

    public String getTituloTotalVisualizar() {
        return tituloTotalVisualizar;
    }

    public void setTituloTotalVisualizar(String tituloTotalVisualizar) {
        this.tituloTotalVisualizar = tituloTotalVisualizar;
    }

    public String getDatosEspecialesConsulta() {
        return datosEspecialesConsulta;
    }

    public void setDatosEspecialesConsulta(String datosEspecialesConsulta) {
        this.datosEspecialesConsulta = datosEspecialesConsulta;
    }

    public String getCamposWhereExtras() {
        return camposWhereExtras;
    }

    public void setCamposWhereExtras(String camposWhereExtras) {
        this.camposWhereExtras = camposWhereExtras;
    }

    public String getTipoOrdenado() {
        return tipoOrdenado;
    }

    public void setTipoOrdenado(String tipoOrdenado) {
        this.tipoOrdenado = tipoOrdenado;
    }

    public Boolean isUsaFiltroCorrida() {
        return usaFiltroCorrida;
    }

    public void setUsaFiltroCorrida(Boolean usaFiltroCorrida) {
        this.usaFiltroCorrida = usaFiltroCorrida;
    }

}
