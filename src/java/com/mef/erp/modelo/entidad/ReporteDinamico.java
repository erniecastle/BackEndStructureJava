/**
 * Clave: JSA01 Autor: Jose Armando Fecha: 23/11/2012 Descripción: Se cambiaron
 * los nombres
 * -----------------------------------------------------------------------------
 * Clave: JSA02 Autor: ARMANDO Fecha: 21/02/2013 Descripción: SE AGREGO EL
 * LISTADO DE ReporteCamposWhere.
 * -----------------------------------------------------------------------------
 * Clave: JSA03 Autor: ARMANDO Fecha: 06/09/2013 Descripción: se agrego un campo
 * clave y se modifico el constraint
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ernesto
 */
@Entity
@Table(name = "ReporteDinamico",
        uniqueConstraints
        = @UniqueConstraint(columnNames = {"razonSocial_ID", "clave"}))
public class ReporteDinamico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    private String nombre;
    private String nombreAbreviado;
    @OneToOne(cascade = CascadeType.ALL)
    private Contenedor contenedor;
    @ManyToOne
    private ReporteFuenteDatos reporteFuenteDatos;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "reporteDinamico", cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<ReporteDatosIncluir> reporteDatosIncluir;
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToMany(mappedBy = "reporteDinamico", cascade = {CascadeType.ALL})
//    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
//    private List<ReporteOpcionGrupos> reporteOpcionGrupos;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "reporteDinamico", cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<ReporteOrdenGrupo> reporteOrdenGrupos;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "reporteDinamico", cascade = {CascadeType.ALL})
    private List<ReporteCamposWhere> reporteCamposWhere;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "reporteDinamico", cascade = {CascadeType.ALL})
    private List<ReporteCamposEncabezado> reporteCamposEncabezado;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "reporteDinamico", cascade = {CascadeType.ALL})
    private List<ReporteDatosResumen> reporteDatosResumen;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] reportFileXml;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] subReportFileXml;
    private Boolean incluirTotalGeneral;
    private Boolean incluirNoPagina;
    private Boolean incluirFechaActual;
    private Boolean usaTodoAnchoPagina;
    private Boolean ocultarColumnas;
    private Boolean mostrarDetalleColumnas;
    private Boolean cortarDatoDetalle;
    private Boolean cortarTituloDetalle;
    private Boolean orientacion;
    private ReporteTipoHoja tipoHoja;
    private Integer ColumnasXPagina;
    private Integer margenDerecho;
    private Integer margenIzquierdo;
    private Integer margenInferior;
    private Integer margenSuperior;
    private Integer Orden;
    private Integer espaciadoColumnas;
    private Integer noFilasEncabezado;
    @ManyToOne
    private RazonSocial razonSocial;
    @OneToOne(cascade = CascadeType.ALL)
    private ReporteEstilos reporteEstiloTotal;
    @OneToOne(cascade = CascadeType.ALL)
    private ReporteEstilos reporteEstiloNoPagina;
    private boolean usaCBB;
    private Integer cbbPosicionX;
    private Integer cbbPosicionY;
    private Integer cbbSizeImagen;
    @Lob
    private byte[] fondo;
    private Integer fondoPosicionX;
    private Integer fondoPosicionY;
    private Integer fondoAncho;
    private Integer fondoLargo;
    private Boolean usaFiltroCorrida;

    public ReporteDinamico() {
    }

    public ReporteDinamico(Long id, String clave, String nombre, String nombreAbreviado, String reporteFuenteDatos) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
        this.nombreAbreviado = nombreAbreviado;
        ReporteFuenteDatos fuenteDatos = new ReporteFuenteDatos();
        fuenteDatos.setNombre(reporteFuenteDatos);
        this.reporteFuenteDatos = fuenteDatos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public Contenedor getContenedor() {
        return contenedor;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public ReporteFuenteDatos getReporteFuenteDatos() {
        return reporteFuenteDatos;
    }

    public void setReporteFuenteDatos(ReporteFuenteDatos reporteFuenteDatos) {
        this.reporteFuenteDatos = reporteFuenteDatos;
    }

    public List<ReporteDatosIncluir> getReporteDatosIncluir() {
        return reporteDatosIncluir;
    }

    public void setReporteDatosIncluir(List<ReporteDatosIncluir> reporteDatosIncluir) {
        this.reporteDatosIncluir = reporteDatosIncluir;
    }

//    public List<ReporteOpcionGrupos> getReporteOpcionGrupos() {
//        return reporteOpcionGrupos;
//    }
//
//    public void setReporteOpcionGrupos(List<ReporteOpcionGrupos> reporteOpcionGrupos) {
//        this.reporteOpcionGrupos = reporteOpcionGrupos;
//    }
    public List<ReporteOrdenGrupo> getReporteOrdenGrupos() {
        return reporteOrdenGrupos;
    }

    public void setReporteOrdenGrupos(List<ReporteOrdenGrupo> reporteOrdenGrupos) {
        this.reporteOrdenGrupos = reporteOrdenGrupos;
    }

    public List<ReporteCamposWhere> getReporteCamposWhere() {
        return reporteCamposWhere;
    }

    public void setReporteCamposWhere(List<ReporteCamposWhere> reporteCamposWhere) {
        this.reporteCamposWhere = reporteCamposWhere;
    }

    public List<ReporteCamposEncabezado> getReporteCamposEncabezado() {
        return reporteCamposEncabezado;
    }

    public void setReporteCamposEncabezado(List<ReporteCamposEncabezado> reporteCamposEncabezado) {
        this.reporteCamposEncabezado = reporteCamposEncabezado;
    }

    public List<ReporteDatosResumen> getReporteDatosResumen() {
        return reporteDatosResumen;
    }

    public void setReporteDatosResumen(List<ReporteDatosResumen> reporteDatosResumen) {
        this.reporteDatosResumen = reporteDatosResumen;
    }
    /*eager load all xml files when select this entity, lazy only load when needed if you call  this get method*/

    public byte[] getReportFileXml() {
        return reportFileXml;
    }

    public void setReportFileXml(byte[] reportFileXml) {
        this.reportFileXml = reportFileXml;
    }

    public byte[] getSubReportFileXml() {
        return subReportFileXml;
    }

    public void setSubReportFileXml(byte[] subReportFileXml) {
        this.subReportFileXml = subReportFileXml;
    }

    public Boolean getIncluirTotalGeneral() {
        return incluirTotalGeneral;
    }

    public void setIncluirTotalGeneral(Boolean incluirTotalGeneral) {
        this.incluirTotalGeneral = incluirTotalGeneral;
    }

    public Boolean getIncluirNoPagina() {
        return incluirNoPagina;
    }

    public void setIncluirNoPagina(Boolean incluirNoPagina) {
        this.incluirNoPagina = incluirNoPagina;
    }

    public Boolean getIncluirFechaActual() {
        return incluirFechaActual;
    }

    public void setIncluirFechaActual(Boolean incluirFechaActual) {
        this.incluirFechaActual = incluirFechaActual;
    }

    public Boolean isUsaTodoAnchoPagina() {
        return usaTodoAnchoPagina;
    }

    public void setUsaTodoAnchoPagina(Boolean usaTodoAnchoPagina) {
        this.usaTodoAnchoPagina = usaTodoAnchoPagina;
    }

    public Boolean isOcultarColumnas() {
        return ocultarColumnas;
    }

    public void setOcultarColumnas(Boolean ocultarColumnas) {
        this.ocultarColumnas = ocultarColumnas;
    }

    public Boolean getMostrarDetalleColumnas() {
        return mostrarDetalleColumnas;
    }

    public void setMostrarDetalleColumnas(Boolean mostrarDetalleColumnas) {
        this.mostrarDetalleColumnas = mostrarDetalleColumnas;
    }

    public Boolean getCortarDatoDetalle() {
        return cortarDatoDetalle;
    }

    public void setCortarDatoDetalle(Boolean cortarDatoDetalle) {
        this.cortarDatoDetalle = cortarDatoDetalle;
    }

    public Boolean getCortarTituloDetalle() {
        return cortarTituloDetalle;
    }

    public void setCortarTituloDetalle(Boolean cortarTituloDetalle) {
        this.cortarTituloDetalle = cortarTituloDetalle;
    }

    public Boolean getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(Boolean orientacion) {
        this.orientacion = orientacion;
    }

    public ReporteTipoHoja getTipoHoja() {
        return tipoHoja;
    }

    public void setTipoHoja(ReporteTipoHoja tipoHoja) {
        this.tipoHoja = tipoHoja;
    }

    public Integer getColumnasXPagina() {
        return ColumnasXPagina;
    }

    public void setColumnasXPagina(Integer ColumnasXPagina) {
        this.ColumnasXPagina = ColumnasXPagina;
    }

    public Integer getMargenDerecho() {
        return margenDerecho;
    }

    public void setMargenDerecho(Integer margenDerecho) {
        this.margenDerecho = margenDerecho;
    }

    public Integer getMargenIzquierdo() {
        return margenIzquierdo;
    }

    public void setMargenIzquierdo(Integer margenIzquierdo) {
        this.margenIzquierdo = margenIzquierdo;
    }

    public Integer getMargenInferior() {
        return margenInferior;
    }

    public void setMargenInferior(Integer margenInferior) {
        this.margenInferior = margenInferior;
    }

    public Integer getMargenSuperior() {
        return margenSuperior;
    }

    public void setMargenSuperior(Integer margenSuperior) {
        this.margenSuperior = margenSuperior;
    }

    public Integer getOrden() {
        return Orden;
    }

    public void setOrden(Integer Orden) {
        this.Orden = Orden;
    }

    public Integer getNoFilasEncabezado() {
        return noFilasEncabezado;
    }

    public void setNoFilasEncabezado(Integer noFilasEncabezado) {
        this.noFilasEncabezado = noFilasEncabezado;
    }

    public Integer getEspaciadoColumnas() {
        return espaciadoColumnas;
    }

    public void setEspaciadoColumnas(Integer espaciadoColumnas) {
        this.espaciadoColumnas = espaciadoColumnas;
    }

    public RazonSocial getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(RazonSocial razonSocial) {
        this.razonSocial = razonSocial;
    }

    public ReporteEstilos getReporteEstiloTotal() {
        return reporteEstiloTotal;
    }

    public void setReporteEstiloTotal(ReporteEstilos reporteEstiloTotal) {
        this.reporteEstiloTotal = reporteEstiloTotal;
    }

    public ReporteEstilos getReporteEstiloNoPagina() {
        return reporteEstiloNoPagina;
    }

    public void setReporteEstiloNoPagina(ReporteEstilos reporteEstiloNoPagina) {
        this.reporteEstiloNoPagina = reporteEstiloNoPagina;
    }

    public boolean isUsaCBB() {
        return usaCBB;
    }

    public void setUsaCBB(boolean usaCBB) {
        this.usaCBB = usaCBB;
    }

    public Integer getCbbPosicionX() {
        return cbbPosicionX;
    }

    public void setCbbPosicionX(Integer cbbPosicionX) {
        this.cbbPosicionX = cbbPosicionX;
    }

    public Integer getCbbPosicionY() {
        return cbbPosicionY;
    }

    public void setCbbPosicionY(Integer cbbPosicionY) {
        this.cbbPosicionY = cbbPosicionY;
    }

    public Integer getCbbSizeImagen() {
        return cbbSizeImagen;
    }

    public void setCbbSizeImagen(Integer cbbSizeImagen) {
        this.cbbSizeImagen = cbbSizeImagen;
    }

    public byte[] getFondo() {
        return fondo;
    }

    public void setFondo(byte[] fondo) {
        this.fondo = fondo;
    }

    public Integer getFondoPosicionX() {
        return fondoPosicionX;
    }

    public void setFondoPosicionX(Integer fondoPosicionX) {
        this.fondoPosicionX = fondoPosicionX;
    }

    public Integer getFondoPosicionY() {
        return fondoPosicionY;
    }

    public void setFondoPosicionY(Integer fondoPosicionY) {
        this.fondoPosicionY = fondoPosicionY;
    }

    public Integer getFondoAncho() {
        return fondoAncho;
    }

    public void setFondoAncho(Integer fondoAncho) {
        this.fondoAncho = fondoAncho;
    }

    public Integer getFondoLargo() {
        return fondoLargo;
    }

    public void setFondoLargo(Integer fondoLargo) {
        this.fondoLargo = fondoLargo;
    }

    public Boolean isUsaFiltroCorrida() {
        return usaFiltroCorrida;
    }

    public void setUsaFiltroCorrida(Boolean usaFiltroCorrida) {
        this.usaFiltroCorrida = usaFiltroCorrida;
    }

}
