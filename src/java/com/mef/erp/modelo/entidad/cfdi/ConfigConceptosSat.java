/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad.cfdi;

import com.mef.erp.modelo.entidad.ConcepNomDefi;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author User
 */
@Entity
@Table(name = "ConfigConceptosSat",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"concepNomDefi_ID"})})
public class ConfigConceptosSat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "concepNomDefi_ID", nullable = false)
    private ConcepNomDefi concepNomDefi;
    @Column(nullable = false)
    private String conceptoSatClave;
    private boolean otroPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConcepNomDefi getConcepNomDefi() {
        return concepNomDefi;
    }

    public void setConcepNomDefi(ConcepNomDefi concepNomDefi) {
        this.concepNomDefi = concepNomDefi;
    }

    public String getConceptoSatClave() {
        return conceptoSatClave;
    }

    public void setConceptoSatClave(String conceptoSatClave) {
        this.conceptoSatClave = conceptoSatClave;
    }

    public boolean isOtroPago() {
        return otroPago;
    }

    public void setOtroPago(boolean otroPago) {
        this.otroPago = otroPago;
    }

}
