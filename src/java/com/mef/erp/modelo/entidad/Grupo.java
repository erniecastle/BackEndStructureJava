/**
 * @author: Victor Lopez Fecha de Creación: 14/09/2011 Compañía: Macropro.
 * Descripción del programa: Entidad de Grupos con Hibernate
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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Grupo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    private String descripcion;
    private String descripcionAbreviada;
//    private Boolean conceptosCompartenExento;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<BaseAfectadaGrupo> baseAfectadaGrupos;

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

//    public Boolean getConceptosCompartenExento() {
//        return conceptosCompartenExento;
//    }
//
//    public void setConceptosCompartenExento(Boolean conceptosCompartenExento) {
//        this.conceptosCompartenExento = conceptosCompartenExento;
//    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionAbreviada() {
        return descripcionAbreviada;
    }

    public void setDescripcionAbreviada(String descripcionAbreviada) {
        this.descripcionAbreviada = descripcionAbreviada;
    }

    public List<BaseAfectadaGrupo> getBaseAfectadaGrupos() {
        return baseAfectadaGrupos;
    }

    public void setBaseAfectadaGrupos(List<BaseAfectadaGrupo> baseAfectadaGrupos) {
        this.baseAfectadaGrupos = baseAfectadaGrupos;
    }
}
