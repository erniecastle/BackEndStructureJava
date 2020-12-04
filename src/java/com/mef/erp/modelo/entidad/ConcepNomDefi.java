/**
 * @author: Victor Lopez Fecha de Creación: 13/09/2011 Compañía: Macropro.
 * Descripción del programa: Entidad de ConceptoDeNomina con Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor: Jose Armando Sanchez Fecha: 29-03-2014 Descripción: se
 * agrego este campo calcularDetallado para indicar si ese concepto se va a
 * calcular con las promociones existentes en ese periodo.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor: Jose Armando Fecha: 15/10/2014 Descripción: Se agregaron
 * los campos mascaras y tipoAccionMascara para agregar la mascara y redondear o
 * truncar.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
//import java.util.Date;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "ConcepNomDefi",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"clave", "fecha"})})
public class ConcepNomDefi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clave;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @ManyToOne(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private ConceptoDeNomina conceptoDeNomina;
    private String descripcion;
    private String descripcionAbreviada;
    private String comportamiento;
    private Naturaleza naturaleza;
    private Tipo tipo;
    private String formulaConcepto;
    private String condicionConcepto;
    @ManyToOne
    private Grupo grupo;
    private boolean activado;
    private boolean imprimirEnListadoNomina;
    private boolean imprimirEnReciboNomina;
    private Integer prioridadDeCalculo;
    private String subCuenta;
    @Transient   //usado para el calculo sdi solo informativo no se guarda en la base datos
    private double resultado;
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "ConcepDeNomiDefini_TipoCorri", joinColumns = {
//        @JoinColumn(name = "concepNomiDefini_clave"),
//        @JoinColumn(name = "conceptNominaDefini_fecha")},
//    inverseJoinColumns = {
//        @JoinColumn(name = "tipoCorridas_id")})
//    private List<TipoCorrida> tipoCorridas;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "concepNomDefi")
//    @JoinColumn(name = "concepNomDefi_ID")
    private List<ConceptoPorTipoCorrida> conceptoPorTipoCorrida;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<ParaConcepDeNom> parametroConceptosDeNominas;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "concepNomDefin", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<BaseAfecConcepNom> baseAfecConcepNomi;
    private String mascara;//JSA02
    private TipoAccionMascaras tipoAccionMascaras;//JSA02
    private String cuentaContable;
    private Integer agregarSubcuentasPor;
    private Integer tipoMovto;
//    private boolean nominaAsimilados;

    public String getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
    }

    public String getCondicionConcepto() {
        return condicionConcepto;
    }

    public void setCondicionConcepto(String condicionConcepto) {
        this.condicionConcepto = condicionConcepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionAbreviada() {
        return descripcionAbreviada;
    }

    public void setDescripcionAbreviada(String descripcionAbreviada) {
        this.descripcionAbreviada = descripcionAbreviada;
    }

    public String getFormulaConcepto() {
        return formulaConcepto;
    }

    public void setFormulaConcepto(String formulaConcepto) {
        this.formulaConcepto = formulaConcepto;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Naturaleza getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(Naturaleza naturaleza) {
        this.naturaleza = naturaleza;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public ConceptoDeNomina getConceptoDeNomina() {
        return conceptoDeNomina;
    }

    public void setConceptoDeNomina(ConceptoDeNomina conceptoDeNomina) {
        this.conceptoDeNomina = conceptoDeNomina;
    }

    public boolean isImprimirEnListadoNomina() {
        return imprimirEnListadoNomina;
    }

    public void setImprimirEnListadoNomina(boolean imprimirEnListadoNomina) {
        this.imprimirEnListadoNomina = imprimirEnListadoNomina;
    }

    public boolean isImprimirEnReciboNomina() {
        return imprimirEnReciboNomina;
    }

    public void setImprimirEnReciboNomina(boolean imprimirEnReciboNomina) {
        this.imprimirEnReciboNomina = imprimirEnReciboNomina;
    }

    public Integer getPrioridadDeCalculo() {
        return prioridadDeCalculo;
    }

    public void setPrioridadDeCalculo(Integer prioridadDeCalculo) {
        this.prioridadDeCalculo = prioridadDeCalculo;
    }

    public String getSubCuenta() {
        return subCuenta;
    }

    public void setSubCuenta(String subCuenta) {
        this.subCuenta = subCuenta;
    }

    public List<ConceptoPorTipoCorrida> getConceptoPorTipoCorrida() {
        return conceptoPorTipoCorrida;
    }

    public void setConceptoPorTipoCorrida(List<ConceptoPorTipoCorrida> conceptoPorTipoCorrida) {
        this.conceptoPorTipoCorrida = conceptoPorTipoCorrida;
    }

//    public List<TipoCorrida> getTipoCorridas() {
//        return tipoCorridas;
//    }
//
//    public void setTipoCorridas(List<TipoCorrida> tipoCorridas) {
//        this.tipoCorridas = tipoCorridas;
//    }
    public List<ParaConcepDeNom> getParametroConceptosDeNominas() {
        return parametroConceptosDeNominas;
    }

    public void setParametroConceptosDeNominas(List<ParaConcepDeNom> parametroConceptosDeNominas) {
        this.parametroConceptosDeNominas = parametroConceptosDeNominas;
    }

    public List<BaseAfecConcepNom> getBaseAfecConcepNomi() {
        return baseAfecConcepNomi;
    }

    public void setBaseAfecConcepNomi(List<BaseAfecConcepNom> baseAfecConcepNomi) {
        this.baseAfecConcepNomi = baseAfecConcepNomi;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public TipoAccionMascaras getTipoAccionMascaras() {
        return tipoAccionMascaras;
    }

    public void setTipoAccionMascaras(TipoAccionMascaras tipoAccionMascaras) {
        this.tipoAccionMascaras = tipoAccionMascaras;
    }

//    public boolean isNominaAsimilados() {
//        return nominaAsimilados;
//    }
//
//    public void setNominaAsimilados(boolean nominaAsimilados) {
//        this.nominaAsimilados = nominaAsimilados;
//    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public Integer getAgregarSubcuentasPor() {
        return agregarSubcuentasPor;
    }

    public void setAgregarSubcuentasPor(Integer agregarSubcuentasPor) {
        this.agregarSubcuentasPor = agregarSubcuentasPor;
    }

    public Integer getTipoMovto() {
        return tipoMovto;
    }

    public void setTipoMovto(Integer tipoMovto) {
        this.tipoMovto = tipoMovto;
    }
}
