/**
 * @author: Daniel
 * Fecha de Creación: 21/05/2011
 * Compañía: FineSoft
 * Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando   
 * Fecha:19-10-2011
 * Descripción:Se agrego la propiedad unique = true a la clave para que no sea duplicada.
 * -----------------------------------------------------------------------------
 */

package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author daniel
 */
@Entity
public class Monedas implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, unique = true)//JSA01
    private String clave;
    @Column(length = 15, nullable = false)
    private String identificador;
    @Column(length = 255, nullable = false)
    private String MonedaSingular;
    @Column(length = 255, nullable = true)
    private String MonedaPlural;
    @Column(length = 255, nullable = true)
    private String CentimosSingular;
    @Column(length = 255, nullable = true)
    private String CentimosPlural;
    @Column(length = 255, nullable = true)
    private String Simbolo;
    @Column(nullable = true)
    private int Decimales;
    @Column(nullable = true)
    private boolean GeneroMoneda;
    @Column(nullable = true)
    private boolean GeneroCentimos;


    public Monedas() {
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

    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getMonedaSingular() {
        return MonedaSingular;
    }
    public void setMonedaSingular(String MonedaSingular) {
        this.MonedaSingular = MonedaSingular;
    }

    public String getMonedaPlural() {
        return MonedaPlural;
    }
    public void setMonedaPlural(String MonedaPlural) {
        this.MonedaPlural = MonedaPlural;
    }

    public String getCentimosSingular() {
        return CentimosSingular;
    }
    public void setCentimosSingular(String CentimosSingular) {
        this.CentimosSingular = CentimosSingular;
    }

    public String getCentimosPlural() {
        return CentimosPlural;
    }
    public void setCentimosPlural(String CentimosPlural) {
        this.CentimosPlural = CentimosPlural;
    }

    public String getSimbolo() {
        return Simbolo;
    }
    public void setSimbolo(String Simbolo) {
        this.Simbolo = Simbolo;
    }

    public int getDecimales() {
        return Decimales;
    }
    public void setDecimales(int Decimales) {
        this.Decimales = Decimales;
    }

    public boolean getGeneroMoneda() {
        return GeneroMoneda;
    }
    public void setGeneroMoneda(boolean GeneroMoneda) {
        this.GeneroMoneda = GeneroMoneda;
    }

    public boolean getGeneroCentimos() {
        return GeneroCentimos;
    }
    public void setGeneroCentimos(boolean GeneroCentimos) {
        this.GeneroCentimos = GeneroCentimos;
    }

}
