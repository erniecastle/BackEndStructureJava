/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 *
 * @author Desarrollo 094
 */
@Entity
public class CamposDimConceptos implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private CampoDIM campoDIM;
    @ManyToOne
    private ConcepNomDefi concepnomDefi;
    @Enumerated(EnumType.STRING)
    private TipoDato tipoDato;
    private String operacion;

    public CamposDimConceptos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CampoDIM getCampoDIM() {
        return campoDIM;
    }

    public void setCampoDIM(CampoDIM campoDIM) {
        this.campoDIM = campoDIM;
    }

    public ConcepNomDefi getConcepnomDefi() {
        return concepnomDefi;
    }

    public void setConcepnomDefi(ConcepNomDefi concepnomDefi) {
        this.concepnomDefi = concepnomDefi;
    }

    public TipoDato getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(TipoDato tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
}
