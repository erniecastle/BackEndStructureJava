/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad.cfdi;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

/**
 *
 * @author User
 */
@Entity
public class CFDIRecibo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "cfdiRecibo_ID", insertable = true, updatable = true)
    @OneToMany(cascade = CascadeType.ALL)
    private List<CFDIReciboConcepto> cfdiReciboConceptos;
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "cfdiRecibo_ID", insertable = true, updatable = true)
    @OneToMany(cascade = CascadeType.ALL)
    private List<CFDIReciboIncapacidad> cfdiReciboIncapacidades;
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "cfdiRecibo_ID", insertable = true, updatable = true)
    @OneToMany(cascade = CascadeType.ALL)
    private List<CFDIReciboHrsExtras> cfdiReciboHrsExtras;
    @Column(length = 25)
    private String serieCFDI;
    private String folioCFDI;
    @Type(type = "text")
    private String sello;
    @Type(type = "text")
    private String cadenaCertificado;
    private String noCertificado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneraInfo;
    @Type(type = "text")
    private String selloTimbrado;
    @Type(type = "text")
    private String cadenaOriginalTimbrado;
    private String certificadoTimbrado;
    private String UUID;
    private StatusTimbrado statusTimbrado;
    private StatusXmlSat statusXmlSat;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraTimbrado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraTimCancelado;
    private String version;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] xmlTimbrado;
    private String motivoCancelacion;
    private Double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CFDIReciboConcepto> getCfdiReciboConceptos() {
        return cfdiReciboConceptos;
    }

    public void setCfdiReciboConceptos(List<CFDIReciboConcepto> cfdiReciboConceptos) {
        this.cfdiReciboConceptos = cfdiReciboConceptos;
    }

    public List<CFDIReciboIncapacidad> getCfdiReciboIncapacidades() {
        return cfdiReciboIncapacidades;
    }

    public void setCfdiReciboIncapacidades(List<CFDIReciboIncapacidad> cfdiReciboIncapacidades) {
        this.cfdiReciboIncapacidades = cfdiReciboIncapacidades;
    }

    public List<CFDIReciboHrsExtras> getCfdiReciboHrsExtras() {
        return cfdiReciboHrsExtras;
    }

    public void setCfdiReciboHrsExtras(List<CFDIReciboHrsExtras> cfdiReciboHrsExtras) {
        this.cfdiReciboHrsExtras = cfdiReciboHrsExtras;
    }

 
    public String getSerieCFDI() {
        return serieCFDI;
    }

    public void setSerieCFDI(String serieCFDI) {
        this.serieCFDI = serieCFDI;
    }

    public String getFolioCFDI() {
        return folioCFDI;
    }

    public void setFolioCFDI(String folioCFDI) {
        this.folioCFDI = folioCFDI;
    }

    public String getSello() {
        return sello;
    }

    public void setSello(String sello) {
        this.sello = sello;
    }

    public String getCadenaCertificado() {
        return cadenaCertificado;
    }

    public void setCadenaCertificado(String cadenaCertificado) {
        this.cadenaCertificado = cadenaCertificado;
    }

    public String getNoCertificado() {
        return noCertificado;
    }

    public void setNoCertificado(String noCertificado) {
        this.noCertificado = noCertificado;
    }

    public Date getFechaGeneraInfo() {
        return fechaGeneraInfo;
    }

    public void setFechaGeneraInfo(Date fechaGeneraInfo) {
        this.fechaGeneraInfo = fechaGeneraInfo;
    }

    public String getSelloTimbrado() {
        return selloTimbrado;
    }

    public void setSelloTimbrado(String selloTimbrado) {
        this.selloTimbrado = selloTimbrado;
    }

    public String getCadenaOriginalTimbrado() {
        return cadenaOriginalTimbrado;
    }

    public void setCadenaOriginalTimbrado(String cadenaOriginalTimbrado) {
        this.cadenaOriginalTimbrado = cadenaOriginalTimbrado;
    }

    public String getCertificadoTimbrado() {
        return certificadoTimbrado;
    }

    public void setCertificadoTimbrado(String certificadoTimbrado) {
        this.certificadoTimbrado = certificadoTimbrado;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public StatusTimbrado isStatusTimbrado() {
        return statusTimbrado;
    }

    public void setStatusTimbrado(StatusTimbrado statusTimbrado) {
        this.statusTimbrado = statusTimbrado;
    }

    public Date getFechaHoraTimbrado() {
        return fechaHoraTimbrado;
    }

    public void setFechaHoraTimbrado(Date fechaHoraTimbrado) {
        this.fechaHoraTimbrado = fechaHoraTimbrado;
    }

    public Date getFechaHoraTimCancelado() {
        return fechaHoraTimCancelado;
    }

    public void setFechaHoraTimCancelado(Date fechaHoraTimCancelado) {
        this.fechaHoraTimCancelado = fechaHoraTimCancelado;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public byte[] getXmlTimbrado() {
        return xmlTimbrado;
    }

    public void setXmlTimbrado(byte[] xmlTimbrado) {
        this.xmlTimbrado = xmlTimbrado;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    public void setMotivoCancelacion(String motivoCancelacion) {
        this.motivoCancelacion = motivoCancelacion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public StatusXmlSat getStatusXmlSat() {
        return statusXmlSat;
    }

    public void setStatusXmlSat(StatusXmlSat statusXmlSat) {
        this.statusXmlSat = statusXmlSat;
    }
    
}
