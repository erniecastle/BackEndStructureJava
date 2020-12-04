/**
 * @author: Ernesto valenzuela Fecha de Creación: 22/07/2013 Compañía: Exito.
 * Descripción del programa: Se creo esta entidad para relacionar los datos
 * encabezados de los reportes
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ernesto
 */
@Entity
public class ReporteOtrosDatosEncabezado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 60000, nullable = true)
    private byte[] logo;
    private String datos;
    @JoinColumn(name = "reporteCamposEncabezado_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(cascade = CascadeType.REFRESH)
    private ReporteCamposEncabezado reporteCamposEncabezado;//JECVC01
    private String otrasPropiedades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public ReporteCamposEncabezado getReporteCamposEncabezado() {
        return reporteCamposEncabezado;
    }

    public void setReporteCamposEncabezado(ReporteCamposEncabezado reporteCamposEncabezado) {
        this.reporteCamposEncabezado = reporteCamposEncabezado;
    }

    public String getOtrasPropiedades() {
        return otrasPropiedades;
    }

    public void setOtrasPropiedades(String otrasPropiedades) {
        this.otrasPropiedades = otrasPropiedades;
    }

}
