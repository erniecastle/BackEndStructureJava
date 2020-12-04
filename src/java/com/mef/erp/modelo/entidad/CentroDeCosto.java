/**
 * @author: Victor Lopez Fecha de Creación: 25/08/2011 Compañía: Macropro.
 * Descripción del programa: Entidad de CentroDeCosto con Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:30/NOV/2001
 * Descripción:Se agrego el campo de razonessociales ya que esta informacion
 * sera por Empresa
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:10/07/2012 Descripción:Se agrego el
 * campo de descripcion previa.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Fecha:06/03/2013 Descripción:Se le agrego que
 * permitiera null en los datos del domicilio.
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

@Entity
@Table(name = "CentroDeCosto",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales_ID", "clave"})})//JSA01
public class CentroDeCosto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false)
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;
    @Column(length = 50, nullable = false)
    private String descripcionPrevia;//JSA02
    @ManyToOne
    private RegistroPatronal registroPatronal;
    @ManyToOne
    private TipoCentroCostos tipoCentroCosto;
    private String calle;//JSA03
    private String colonia;//JSA03
    private String telefono;
    private String numeroExterior;//JSA03
    private String numeroInterior;
    @ManyToOne
    private Cp cp;//JSA03
    private String subCuenta;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)//JSA01
    private RazonesSociales razonesSociales;//JSA01
    @ManyToOne
    private Ciudades ciudades;
    @ManyToOne
    private Municipios municipios;
    @ManyToOne
    private Estados estados;
    @ManyToOne
    private Paises paises;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public RegistroPatronal getRegistroPatronal() {
        return registroPatronal;
    }

    public void setRegistroPatronal(RegistroPatronal registroPatronal) {
        this.registroPatronal = registroPatronal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSubCuenta() {
        return subCuenta;
    }

    public void setSubCuenta(String subCuenta) {
        this.subCuenta = subCuenta;
    }

    public TipoCentroCostos getTipoCentroCosto() {
        return tipoCentroCosto;
    }

    public void setTipoCentroCosto(TipoCentroCostos tipoCentroCosto) {
        this.tipoCentroCosto = tipoCentroCosto;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public String getDescripcionPrevia() {
        return descripcionPrevia;
    }

    public void setDescripcionPrevia(String descripcionPrevia) {
        this.descripcionPrevia = descripcionPrevia;
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
