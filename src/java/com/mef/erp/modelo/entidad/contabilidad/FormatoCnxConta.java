/**
 * @author: Ernesto Castillo Fecha de Creación: 23/11/2015 Compañía: Exito
 * Software. Descripción del programa: Entidad de FormatoCnXConta para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad.contabilidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ernesto
 */
@Entity
public class FormatoCnxConta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @Column(length = 255)
    private String descripcion;
    @Column(length = 20)
    private Integer modoConcepto;
    @Column(length = 255)
    private String conceptoPoliza;
    //@Column(length = 20)
    private Boolean separarPoliXCfdi;
    @Column(length = 255)
    private String diario;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "formatoCnxConta")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<FormatosCnxContaDet> formatosCnxContaDet;

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

    public Integer getModoConcepto() {
        return modoConcepto;
    }

    public void setModoConcepto(Integer modoConcepto) {
        this.modoConcepto = modoConcepto;
    }

    public String getConceptoPoliza() {
        return conceptoPoliza;
    }

    public void setConceptoPoliza(String conceptoPoliza) {
        this.conceptoPoliza = conceptoPoliza;
    }

    public Boolean isSepararPoliXCfdi() {
        return separarPoliXCfdi;
    }

    public void setSepararPoliXCfdi(Boolean separarPoliXCfdi) {
        this.separarPoliXCfdi = separarPoliXCfdi;
    }

   

    public String getDiario() {
        return diario;
    }

    public void setDiario(String diario) {
        this.diario = diario;
    }

    public List<FormatosCnxContaDet> getFormatosCnxContaDet() {
        return formatosCnxContaDet;
    }

    public void setFormatosCnxContaDet(List<FormatosCnxContaDet> formatosCnxContaDet) {
        this.formatosCnxContaDet = formatosCnxContaDet;
    }

}
