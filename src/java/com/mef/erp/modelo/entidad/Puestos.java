/**
 * @author: daniel Fecha de Creación: --/--/---- Compañía: FineSoft. Descripción
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:20/06/2012 Descripción:Se quito el campo
 * de promedio.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:10/07/2012 Descripción:Se agrego el
 * campo de descripcion previa.
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

/**
 *
 * @author daniel
 */
@Entity
public class Puestos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;
    @Column(length = 20, nullable = false)
    private String descripcionPrevia;//JSA02
    @Column(length = 255, nullable = false)
    private String funciones;
    private float salarioTabular;
    private float maximo;
    private float minimo;
    @ManyToOne
    private CategoriasPuestos categoriasPuestos;
    @ManyToOne
    private RegistroPatronal registroPatronal;
    private Boolean directivo;

    public Long getId() {
        return id;
    }

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
     * @return the funciones
     */
    public String getFunciones() {
        return funciones;
    }

    /**
     * @param funciones the funciones to set
     */
    public void setFunciones(String funciones) {
        this.funciones = funciones;
    }

    /**
     * @return the categoriasPuestos
     */
    public CategoriasPuestos getCategoriasPuestos() {
        return categoriasPuestos;
    }

    /**
     * @param categoriasPuestos the categoriasPuestos to set
     */
    public void setCategoriasPuestos(CategoriasPuestos categoriasPuestos) {
        this.categoriasPuestos = categoriasPuestos;
    }

    public float getSalarioTabular() {
        return salarioTabular;
    }

    public void setSalarioTabular(float salarioTabular) {
        this.salarioTabular = salarioTabular;
    }

    /**
     * @return the maximo
     */
    public float getMaximo() {
        return maximo;
    }

    /**
     * @param maximo the maximo to set
     */
    public void setMaximo(float maximo) {
        this.maximo = maximo;
    }

    /**
     * @return the minimo
     */
    public float getMinimo() {
        return minimo;
    }

    /**
     * @param minimo the minimo to set
     */
    public void setMinimo(float minimo) {
        this.minimo = minimo;
    }

    /**
     * @return the registroPatronal
     */
    public RegistroPatronal getRegistroPatronal() {
        return registroPatronal;
    }

    /**
     * @param registroPatronal the registroPatronal to set
     */
    public void setRegistroPatronal(RegistroPatronal registroPatronal) {
        this.registroPatronal = registroPatronal;
    }

    public String getDescripcionPrevia() {
        return descripcionPrevia;
    }

    public void setDescripcionPrevia(String descripcionPrevia) {
        this.descripcionPrevia = descripcionPrevia;
    }

    public Boolean isDirectivo() {
        return directivo;
    }

    public void setDirectivo(Boolean directivo) {
        this.directivo = directivo;
    }

}
