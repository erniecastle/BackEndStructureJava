/**
 * @author: Ernesto Castillo Fecha de Creación: --/--/2011 Compañía: Exito
 * Software. Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 02-12-2011
 * Descripción: Se agrego el campo de Razon social ya que esta informacion es
 * por empresa.
 * -----------------------------------------------------------------------------
 * Clave: JSA02 Autor: Jose Armando Fecha: 14-02-2014 Descripción: Se agrego el
 * campo riesgo puesto para el proceso del timbrado de nomina
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

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
 * @author Ernesto Castillo
 */
@Entity
@Table(name = "RegistroPatronal", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"razonesSociales_ID", "clave"})})//JSA01
public class RegistroPatronal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 255, nullable = false)
    private String clave;
    @Column(length = 255, nullable = false)
    private String nombreregtpatronal;
    @Column(length = 255, nullable = false)
    private String registropatronal;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)//JSA01
    private RazonesSociales razonesSociales;//JDRA01
    private String calle;
    private String numeroex;
    private String numeroin;
    private String colonia;
    @ManyToOne
    private Cp cp;
    private String telefono;
    private String fax;
    private String correoelec;
    private String paginainter;
    private Boolean convenio;
    private String clavedelegacion;
    private String delegacion;
    private String clavesubdelegacion;
    private String subdelegacion;
    private String riesgoPuesto;//JSA02
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreregtpatronal() {
        return nombreregtpatronal;
    }

    public void setNombreregtpatronal(String nombreregtpatronal) {
        this.nombreregtpatronal = nombreregtpatronal;
    }

    public String getRegistropatronal() {
        return registropatronal;
    }

    public void setRegistropatronal(String registropatronal) {
        this.registropatronal = registropatronal;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }//JDRA01

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

    public String getNumeroin() {
        return numeroin;
    }

    public void setNumeroin(String numeroin) {
        this.numeroin = numeroin;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCorreoelec() {
        return correoelec;
    }

    public void setCorreoelec(String correoelec) {
        this.correoelec = correoelec;
    }

    public String getPaginainter() {
        return paginainter;
    }

    public void setPaginainter(String paginainter) {
        this.paginainter = paginainter;
    }

    public Boolean getConvenio() {
        return convenio;
    }

    public void setConvenio(Boolean convenio) {
        this.convenio = convenio;
    }

    public String getClavedelegacion() {
        return clavedelegacion;
    }

    public void setClavedelegacion(String clavedelegacion) {
        this.clavedelegacion = clavedelegacion;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getClavesubdelegacion() {
        return clavesubdelegacion;
    }

    public void setClavesubdelegacion(String clavesubdelegacion) {
        this.clavesubdelegacion = clavesubdelegacion;
    }

    public String getSubdelegacion() {
        return subdelegacion;
    }

    public void setSubdelegacion(String subdelegacion) {
        this.subdelegacion = subdelegacion;
    }

    public String getRiesgoPuesto() {
        return riesgoPuesto;
    }

    public void setRiesgoPuesto(String riesgoPuesto) {
        this.riesgoPuesto = riesgoPuesto;
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
