/**
 * @author: Ernesto Castillo Fecha de Creación: 23/01/2014 Compañía: Exito
 * Software. Descripción del programa: Entidad de importador de campos para
 * Hibernate.
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ernesto
 */
@Entity
@Table(name = "ImportaCampos",
        uniqueConstraints
        = @UniqueConstraint(columnNames = {"id", "clave"}))
public class ImportaCampos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    private String nombreConfiguracion;
    private String nombreVentana;
    private Integer fila;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "importaCampos", cascade = {CascadeType.ALL})
    private List<DetalleImportaCampos> detalleImportaCampos;

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

    public String getNombreConfiguracion() {
        return nombreConfiguracion;
    }

    public void setNombreConfiguracion(String nombreConfiguracion) {
        this.nombreConfiguracion = nombreConfiguracion;
    }

    public String getNombreVentana() {
        return nombreVentana;
    }

    public void setNombreVentana(String nombreVentana) {
        this.nombreVentana = nombreVentana;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public List<DetalleImportaCampos> getDetalleImportaCampos() {
        return detalleImportaCampos;
    }

    public void setDetalleImportaCampos(List<DetalleImportaCampos> detalleImportaCampos) {
        this.detalleImportaCampos = detalleImportaCampos;
    }

}
