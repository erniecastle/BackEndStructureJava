/**
 * @author: Ernesto Castillo Fecha de Creación: 21/05/2011 Compañía: Exito
 * Software. Descripción del programa: Entidad de elementos de aplicacion para
 * Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String, se agregaron tamaños de columnas
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:19-10-2011 Descripción:Se agrego la
 * propiedad unique = true a la clave para que no sea duplicada.
 * -----------------------------------------------------------------------------
 * Clave: DRO01 Autor: Dayane Rocha Fecha: 26/02/2014 Descripción: Se agregaron
 * los campos necesarios para Recibo Electronico.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class RazonesSociales implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)//JSA01
    private String clave;//AAP01
    @Column(length = 255, nullable = false)//AAP01
    private String razonsocial;
    @Column(length = 255, nullable = false)//AAP01
    private String representantelegal;
    @Column(length = 13, nullable = false, unique = true)//JSA011
    private String rfc;
    @Column(length = 255, nullable = false)//AAP01
    private String calle;
    @Column(length = 30, nullable = false)//AAP01
    private String numeroex;
    @Column(length = 30, nullable = false)//AAP01
    private String numeroin;
    @Column(length = 255, nullable = false)//AAP01
    private String colonia;
    @Column(length = 20, nullable = false)//AAP01
    private String telefono;
    @Lob
    private byte[] certificadoSAT;
    @Lob
    private byte[] llaveSAT;
    private String ubicacionXML;
    private String rutaCert;
    private String rutaLlave;
    private String password;
    private String regimenFiscal;
    private String folio;
    private String serie;
    private String descripcionRecibo;
    @ManyToOne
    private ConfiguraTimbrado configuraTimbrado;
    @ManyToOne
    private Cp cp;
    @ManyToOne
    private Ciudades ciudades;
    @ManyToOne
    private Municipios municipios;
    @ManyToOne
    private Estados estados;
    @ManyToOne
    private Paises paises;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {//AAP01
        return clave;
    }

    public void setClave(String clave) {//AAP01
        this.clave = clave;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRepresentantelegal() {
        return representantelegal;
    }

    public void setRepresentantelegal(String representantelegal) {
        this.representantelegal = representantelegal;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroex() {
        return numeroex;
    }

    public void setNumeroex(String numeroex) {
        this.numeroex = numeroex;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Cp getCp() {
        return cp;
    }

    public void setCp(Cp cp) {
        this.cp = cp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumeroin() {
        return numeroin;
    }

    public void setNumeroin(String numeroin) {
        this.numeroin = numeroin;
    }

    public byte[] getCertificadoSAT() {
        return certificadoSAT;
    }

    public void setCertificadoSAT(byte[] certificadoSAT) {
        this.certificadoSAT = certificadoSAT;
    }

    public byte[] getLlaveSAT() {
        return llaveSAT;
    }

    public void setLlaveSAT(byte[] llaveSAT) {
        this.llaveSAT = llaveSAT;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUbicacionXML() {
        return ubicacionXML;
    }

    public void setUbicacionXML(String ubicacionXML) {
        this.ubicacionXML = ubicacionXML;
    }

    public String getRutaCert() {
        return rutaCert;
    }

    public void setRutaCert(String rutaCert) {
        this.rutaCert = rutaCert;
    }

    public String getRutaLlave() {
        return rutaLlave;
    }

    public void setRutaLlave(String rutaLlave) {
        this.rutaLlave = rutaLlave;
    }

    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    public void setRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDescripcionRecibo() {
        return descripcionRecibo;
    }

    public void setDescripcionRecibo(String descripcionRecibo) {
        this.descripcionRecibo = descripcionRecibo;
    }

    public ConfiguraTimbrado getConfiguraTimbrado() {
        return configuraTimbrado;
    }

    public void setConfiguraTimbrado(ConfiguraTimbrado configuraTimbrado) {
        this.configuraTimbrado = configuraTimbrado;
    }

    public Ciudades getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }

    public Municipios getMunicipios() {
        return municipios;
    }

    public void setMunicipios(Municipios municipios) {
        this.municipios = municipios;
    }

    public Estados getEstados() {
        return estados;
    }

    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    public Paises getPaises() {
        return paises;
    }

    public void setPaises(Paises paises) {
        this.paises = paises;
    }

}
