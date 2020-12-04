/**
 * @author: Ernesto Castillo Fecha de Creación: 27/01/2014 Compañía: Exito
 * Software. Descripción del programa: Entidad de detalle importador de campos
 * para Hibernate.
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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ernesto
 */
@Entity
public class DetalleImportaCampos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    private ImportaCampos importaCampos;
    @Column(length = 30, nullable = false)
    private Integer noColumna;
    private Integer orden;
    private String campo;

    public DetalleImportaCampos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImportaCampos getImportaCampos() {
        return importaCampos;
    }

    public void setImportaCampos(ImportaCampos importaCampos) {
        this.importaCampos = importaCampos;
    }

    public Integer getNoColumna() {
        return noColumna;
    }

    public void setNoColumna(Integer noColumna) {
        this.noColumna = noColumna;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

}
