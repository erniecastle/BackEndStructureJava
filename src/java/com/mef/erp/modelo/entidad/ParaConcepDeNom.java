/**
 * @author: Victor Lopez Fecha de Creación: 14/09/2011 Compañía: Macropro.
 * Descripción del programa: Entidad de ParametrosDeNomina con Hibernate
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ParaConcepDeNom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer numero;
    private String descripcion;
    @Column(length = 30)
    private String unidad;
    @Column(length = 30)
    private String mascara;
    @Column(length = 30)
    private String tipo;
    private ClasificadorParametro clasificadorParametro;
////    private String formulaParametro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public ClasificadorParametro getClasificadorParametro() {
        return clasificadorParametro;
    }

    public void setClasificadorParametro(ClasificadorParametro clasificadorParametro) {
        this.clasificadorParametro = clasificadorParametro;
    }
}
