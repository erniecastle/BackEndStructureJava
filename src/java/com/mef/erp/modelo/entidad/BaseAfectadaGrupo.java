/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BaseAfectadaGrupo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private BaseNomina baseNomina;
    private String formulaExenta;
    private String periodoExentoISR;
    private int tipoAfecta;
    @ManyToOne
    private Grupo grupo;

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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
