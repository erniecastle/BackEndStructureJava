/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author daniel
 */
@Entity
public class Grupos implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;
    @Column(length = 255, nullable = false)
    private String abreviacion;
    @Column(nullable = true)
    private boolean compartenExento;
    @Column(nullable = true)
    private boolean ISR;
    @Column(nullable = true)
    private boolean IMSS;
    @Column(nullable = true)
    private boolean despensa;
    @Column(nullable = true)
    private boolean fondoAhorro;
    @Column(nullable = true)
    private boolean PTU;
    @Column(nullable = true)
    private boolean aguinaldo;
    @Column(nullable = true)
    private boolean ISN;
    @Column(length = 255, nullable = false)
    private String formulaISR;
    @Column(length = 255, nullable = false)
    private String formulaIMSS;
    @ManyToOne
    private TipoNomina tipoNomina;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the abreviacion
     */
    public String getAbreviacion() {
        return abreviacion;
    }

    /**
     * @param abreviacion the abreviacion to set
     */
    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    /**
     * @return the compartenExento
     */
    public boolean isCompartenExento() {
        return compartenExento;
    }

    /**
     * @param compartenExento the compartenExento to set
     */
    public void setCompartenExento(boolean compartenExento) {
        this.compartenExento = compartenExento;
    }

    /**
     * @return the ISR
     */
    public boolean isISR() {
        return ISR;
    }

    /**
     * @param ISR the ISR to set
     */
    public void setISR(boolean ISR) {
        this.ISR = ISR;
    }

    /**
     * @return the IMSS
     */
    public boolean isIMSS() {
        return IMSS;
    }

    /**
     * @param IMSS the IMSS to set
     */
    public void setIMSS(boolean IMSS) {
        this.IMSS = IMSS;
    }

    /**
     * @return the despensa
     */
    public boolean isDespensa() {
        return despensa;
    }

    /**
     * @param despensa the despensa to set
     */
    public void setDespensa(boolean despensa) {
        this.despensa = despensa;
    }

    /**
     * @return the fondoAhorro
     */
    public boolean isFondoAhorro() {
        return fondoAhorro;
    }

    /**
     * @param fondoAhorro the fondoAhorro to set
     */
    public void setFondoAhorro(boolean fondoAhorro) {
        this.fondoAhorro = fondoAhorro;
    }

    /**
     * @return the PTU
     */
    public boolean isPTU() {
        return PTU;
    }

    /**
     * @param PTU the PTU to set
     */
    public void setPTU(boolean PTU) {
        this.PTU = PTU;
    }

    /**
     * @return the aguinaldo
     */
    public boolean isAguinaldo() {
        return aguinaldo;
    }

    /**
     * @param aguinaldo the aguinaldo to set
     */
    public void setAguinaldo(boolean aguinaldo) {
        this.aguinaldo = aguinaldo;
    }

    /**
     * @return the ISN
     */
    public boolean isISN() {
        return ISN;
    }

    /**
     * @param ISN the ISN to set
     */
    public void setISN(boolean ISN) {
        this.ISN = ISN;
    }

    /**
     * @return the formulaISR
     */
    public String getFormulaISR() {
        return formulaISR;
    }

    /**
     * @param formulaISR the formulaISR to set
     */
    public void setFormulaISR(String formulaISR) {
        this.formulaISR = formulaISR;
    }

    /**
     * @return the formulaIMSS
     */
    public String getFormulaIMSS() {
        return formulaIMSS;
    }

    /**
     * @param formulaIMSS the formulaIMSS to set
     */
    public void setFormulaIMSS(String formulaIMSS) {
        this.formulaIMSS = formulaIMSS;
    }

    /**
     * @return the tipoNomina
     */
    public TipoNomina getTipoNomina() {
        return tipoNomina;
    }

    /**
     * @param tipoNomina the tipoNomina to set
     */
    public void setTipoNomina(TipoNomina tipoNomina) {
        this.tipoNomina = tipoNomina;
    }
}
