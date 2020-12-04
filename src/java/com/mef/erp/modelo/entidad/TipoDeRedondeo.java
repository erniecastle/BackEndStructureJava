/**
 * @author: Victor Lopez
 * Fecha de Creación: 06/09/2011
 * Compañía: Macropro.
 * Descripción del programa: Entidad de TipoDeRedondeo con Hibernate
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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;

@Entity
public class TipoDeRedondeo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private TipoDeValor tipoDeValor;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<DatosTipoValor> datosTipoValor;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoDeValor getTipoDeValor() {
        return tipoDeValor;
    }

    public void setTipoDeValor(TipoDeValor tipoDeValor) {
        this.tipoDeValor = tipoDeValor;
    }

    public List<DatosTipoValor> getDatosTipoValor() {
        return datosTipoValor;
    }

    public void setDatosTipoValor(List<DatosTipoValor> datosTipoValor) {
        this.datosTipoValor = datosTipoValor;
    }
}
