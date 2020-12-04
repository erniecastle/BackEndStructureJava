/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BaseAfecConcepNom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private BaseNomina baseNomina;
    @ManyToOne(cascade = CascadeType.ALL)
    private ConcepNomDefi concepNomDefin;
    private String formulaExenta;
    private String periodoExentoISR;
    private int tipoAfecta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseNomina getBaseNomina() {
        return baseNomina;
    }

    public void setBaseNomina(BaseNomina baseNomina) {
        this.baseNomina = baseNomina;
    }

    public ConcepNomDefi getConceptoDeNominaDefinicion() {
        return concepNomDefin;
    }

    public void setConceptoDeNominaDefinicion(ConcepNomDefi conceptoDeNominaDefinicion) {
        this.concepNomDefin = conceptoDeNominaDefinicion;
    }

    public int getTipoAfecta() {
        return tipoAfecta;
    }

    public void setTipoAfecta(int tipoAfecta) {
        this.tipoAfecta = tipoAfecta;
    }

    public String getFormulaExenta() {
        return formulaExenta;
    }

    public void setFormulaExenta(String formulaExenta) {
        this.formulaExenta = formulaExenta;
    }

    public String getPeriodoExentoISR() {
        return periodoExentoISR;
    }

    public void setPeriodoExentoISR(String periodoExentoISR) {
        this.periodoExentoISR = periodoExentoISR;
    }
}
