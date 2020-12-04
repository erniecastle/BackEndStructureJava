/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class AsignaTipoReporte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private TipoReporte tipoReporte;
    
    @ManyToOne
    private ReporteDinamico reporteDinamico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReporteDinamico getReporteDinamico() {
        return reporteDinamico;
    }

    public void setReporteDinamico(ReporteDinamico reporteDinamico) {
        this.reporteDinamico = reporteDinamico;
    }

    public TipoReporte getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(TipoReporte tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
}
