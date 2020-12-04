/**
 * @author: Ernesto Castillo Fecha de Creación: 23/11/2015 Compañía: Exito
 * Software. Descripción del programa: Entidad de FormatosCnxContaDet para
 * Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad.contabilidad;

import com.mef.erp.modelo.entidad.ConcepNomDefi;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ernesto
 */
@Entity
public class FormatosCnxContaDet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 255)
    private String elemento;
    @Column(length = 20)
    private Integer tipoMovto;
    private Double factor;
    @Column(length = 20)
    private Integer agrupacion;
    @Column(length = 20)
    private Integer modoConcepto;
    @Column(length = 255)
    private String conceptoMovto;
    @Column(length = 25)
    private Integer modoReferencia;
    @Column(length = 255)
    private String referenciaMovto;
    @Column(length = 20)
    private Integer adjuntCfdi;
    @Column(length = 25)
    private Boolean incluirEnCeros;
    @Column(length = 25)
    private Integer incluirCC;
    @Lob
    @Column(length = 60000, nullable = true)
    private byte[] filtro;
    @ManyToOne
    private CuentasContables cuentasContables;
    @ManyToOne
    private DatosDisponiblesCxnConta datosDisponiblesCxnConta;
    @ManyToOne
    private FormatoCnxConta formatoCnxConta;
    @Column(length = 30)
    private String valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public Integer getTipoMovto() {
        return tipoMovto;
    }

    public void setTipoMovto(Integer tipoMovto) {
        this.tipoMovto = tipoMovto;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public Integer getAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(Integer agrupacion) {
        this.agrupacion = agrupacion;
    }

    public Integer getModoConcepto() {
        return modoConcepto;
    }

    public void setModoConcepto(Integer modoConcepto) {
        this.modoConcepto = modoConcepto;
    }

    public String getConceptoMovto() {
        return conceptoMovto;
    }

    public void setConceptoMovto(String conceptoMovto) {
        this.conceptoMovto = conceptoMovto;
    }

    public Integer getModoReferencia() {
        return modoReferencia;
    }

    public void setModoReferencia(Integer modoReferencia) {
        this.modoReferencia = modoReferencia;
    }

    public String getReferenciaMovto() {
        return referenciaMovto;
    }

    public void setReferenciaMovto(String referenciaMovto) {
        this.referenciaMovto = referenciaMovto;
    }

    public Integer getAdjuntCfdi() {
        return adjuntCfdi;
    }

    public void setAdjuntCfdi(Integer adjuntCfdi) {
        this.adjuntCfdi = adjuntCfdi;
    }

    public Boolean isIncluirEnCeros() {
        return incluirEnCeros;
    }

    public void setIncluirEnCeros(Boolean incluirEnCeros) {
        this.incluirEnCeros = incluirEnCeros;
    }

   

    public Integer getIncluirCC() {
        return incluirCC;
    }

    public void setIncluirCC(Integer incluirCC) {
        this.incluirCC = incluirCC;
    }

    public byte[] getFiltro() {
        return filtro;
    }

    public void setFiltro(byte[] filtro) {
        this.filtro = filtro;
    }

    public CuentasContables getCuentasContables() {
        return cuentasContables;
    }

    public void setCuentasContables(CuentasContables cuentasContables) {
        this.cuentasContables = cuentasContables;
    }

    public DatosDisponiblesCxnConta getDatosDisponiblesCxnConta() {
        return datosDisponiblesCxnConta;
    }

    public void setDatosDisponiblesCxnConta(DatosDisponiblesCxnConta datosDisponiblesCxnConta) {
        this.datosDisponiblesCxnConta = datosDisponiblesCxnConta;
    }

    public FormatoCnxConta getFormatoCnxConta() {
        return formatoCnxConta;
    }

    public void setFormatoCnxConta(FormatoCnxConta formatoCnxConta) {
        this.formatoCnxConta = formatoCnxConta;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
