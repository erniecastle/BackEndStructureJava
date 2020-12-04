/**
 * @author: Ernesto Castillo Fecha de Creación: 30/11/2011 Compañía: Exito
 * Software. Descripción del programa: Entidad de Excepciones para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 07/06/2013 Descripcion: Se cambio el
 * tipo de naturaleza de Int a Naturaleza. se agrego una relacion con respecto a
 * los conceptos. y se agrego TipoDatoExcepcion para indicar el dato a ingresar.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:29/10/2014 Descripción:Este campo es para indicar que esa excepcion va ser unica por dia.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Excepciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 35, nullable = false, unique = true)
    private String clave;
    @Column(length = 100, nullable = false)
    private int orden;
    @Column(length = 255, nullable = false)
    private String excepcion;
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "ParametrosConNom")
    @ManyToMany(mappedBy = "excepciones")
    private List<ConfiguracionAsistencias> configuracionAsistencias;
    private Naturaleza naturaleza;//JSA01
    private TipoDatoExcepcion tipoDatoExcepcion;//JSA01
    //Naturaleza 0 para negativo (Deduccion)y 1 (Percepcion) para positivo
    @ManyToOne
    private ConcepNomDefi concepNomDefi;//JSA01
    private Boolean unico;//jsa02 Este campo es para indicar que esa excepcion va ser unica por dia.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getExcepcion() {
        return excepcion;
    }

    public void setExcepcion(String excepcion) {
        this.excepcion = excepcion;
    }

    public List<ConfiguracionAsistencias> getConfiguracionAsistencias() {
        return configuracionAsistencias;
    }

    public void setConfiguracionAsistencias(List<ConfiguracionAsistencias> configuracionAsistencias) {
        this.configuracionAsistencias = configuracionAsistencias;
    }

    public Naturaleza getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(Naturaleza naturaleza) {
        this.naturaleza = naturaleza;
    }

    public TipoDatoExcepcion getTipoDatoExcepcion() {
        return tipoDatoExcepcion;
    }

    public void setTipoDatoExcepcion(TipoDatoExcepcion tipoDatoExcepcion) {
        this.tipoDatoExcepcion = tipoDatoExcepcion;
    }

    public ConcepNomDefi getConcepNomDefi() {
        return concepNomDefi;
    }

    public void setConcepNomDefi(ConcepNomDefi concepNomDefi) {
        this.concepNomDefi = concepNomDefi;
    }

    public Boolean getUnico() {
        return unico;
    }

    public void setUnico(Boolean unico) {
        this.unico = unico;
    }

}
