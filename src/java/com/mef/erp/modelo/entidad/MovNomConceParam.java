/**
 * @author: José Ernesto Valenzuela Castillo Fecha de Creación: 12/03/2012
 * Compañía: Exito Software. Descripción del programa: MovimientoNomConcepParam
 * para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MovNomConceParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private MovNomConcep movNomConcep;
    @ManyToOne
    private ParaConcepDeNom paraConcepDeNom;
    private String valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovNomConcep getMovNomConcep() {
        return movNomConcep;
    }

    public void setMovNomConcep(MovNomConcep movNomConcep) {
        this.movNomConcep = movNomConcep;
    }

    public ParaConcepDeNom getParaConcepDeNom() {
        return paraConcepDeNom;
    }

    public void setParaConcepDeNom(ParaConcepDeNom paraConcepDeNom) {
        this.paraConcepDeNom = paraConcepDeNom;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public static MovNomConceParam copiaMovBaseAfecta(MovNomConceParam movNomConceParam) {
        MovNomConceParam conceParam = new MovNomConceParam();
        conceParam.setMovNomConcep(movNomConceParam.getMovNomConcep());
        conceParam.setParaConcepDeNom(movNomConceParam.getParaConcepDeNom());
        conceParam.setValor(movNomConceParam.getValor());
        conceParam.setId(movNomConceParam.getId());
        return conceParam;
    }
}
