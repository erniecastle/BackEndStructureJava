/**
 * @author: Victor Lopez
 * Fecha de Creación: 10/03/2012
 * Compañía: Macropro
 * Descripción del programa: MovNominaBaseAfecta para  Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MovNomBaseAfecta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private MovNomConcep movNomConcep;
    @ManyToOne
    private BaseAfecConcepNom baseAfecConcepNom;
    private Double resultado;
    private Double resultadoExento;
    @Column(nullable = false)
    private int uso;

    public BaseAfecConcepNom getBaseAfecConcepNom() {
        return baseAfecConcepNom;
    }

    public void setBaseAfecConcepNom(BaseAfecConcepNom baseAfecConcepNom) {
        this.baseAfecConcepNom = baseAfecConcepNom;
    }

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

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public Double getResultadoExento() {
        return resultadoExento;
    }

    public void setResultadoExento(Double resultadoExento) {
        this.resultadoExento = resultadoExento;
    }

    public int getUso() {
        return uso;
    }

    public void setUso(int uso) {
        this.uso = uso;
    }

    public static MovNomBaseAfecta copiaMovBaseAfecta(MovNomBaseAfecta movBaseAfecta) {
        MovNomBaseAfecta baseAfecta = new MovNomBaseAfecta();
        baseAfecta.setBaseAfecConcepNom(movBaseAfecta.getBaseAfecConcepNom());
        baseAfecta.setMovNomConcep(movBaseAfecta.getMovNomConcep());
        baseAfecta.setResultado(movBaseAfecta.getResultado());
        baseAfecta.setResultadoExento(movBaseAfecta.getResultadoExento());
        baseAfecta.setUso(movBaseAfecta.getUso());
        baseAfecta.setId(movBaseAfecta.getId());
        return baseAfecta;
    }
  
}
