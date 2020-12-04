/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Desarrollo 094
 */
@Entity
public class AguinaldoConfiguracion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false/*, columnDefinition = "Id auto_incremento"*/)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @Column(nullable = false)
    private Integer pagarEnUnaSolaExhibicion;//posibles valores 0 y 1
    @Column(nullable = false)
    private Integer numPagos;
    
    private Integer modoCalculo;//Opciones: Se toma parte proporcional, Se calcula todo y se divide o se intenta descontar todo en primer pago

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public Integer getPagarEnUnaSolaExhibicion() {
        return pagarEnUnaSolaExhibicion;
    }

    public void setPagarEnUnaSolaExhibicion(Integer pagarEnUnaSolaExhibicion) {
        this.pagarEnUnaSolaExhibicion = pagarEnUnaSolaExhibicion;
    }

    public Integer getNumPagos() {
        return numPagos;
    }

    public void setNumPagos(Integer numPagos) {
        this.numPagos = numPagos;
    }

    public Integer getModoCalculo() {
        return modoCalculo;
    }

    public void setModoCalculo(Integer modoCalculo) {
        this.modoCalculo = modoCalculo;
    }

}
