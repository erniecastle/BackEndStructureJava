/**
 * @author: Armando Fecha de Creacion: 21/02/2012 Compañía: Macropro.
 * Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES: Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ReporteCamposWhere")
public class ReporteCamposWhere implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String camposWhere;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private ReporteDinamico reporteDinamico;//JECVC01 
    @Transient
    private CamposNodoConsulta camposNodoConsulta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCamposWhere() {
        return camposWhere;
    }

    public void setCamposWhere(String camposWhere) {
        this.camposWhere = camposWhere;
    }

    public ReporteDinamico getReporteDinamico() {
        return reporteDinamico;
    }

    public void setReporteDinamico(ReporteDinamico reporteDinamico) {
        this.reporteDinamico = reporteDinamico;
    }

    public CamposNodoConsulta getCamposNodoConsulta() {
        return camposNodoConsulta;
    }

    public void setCamposNodoConsulta(CamposNodoConsulta camposNodoConsulta) {
        this.camposNodoConsulta = camposNodoConsulta;
    }
}
