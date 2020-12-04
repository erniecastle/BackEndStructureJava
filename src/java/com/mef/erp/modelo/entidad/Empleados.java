/**
 * @author: daniel Fecha de Creación: --/--/---- Compañía: Finesoft Descripción
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES: Clave:JSA01 Autor:Jose Armando Fecha:10/07/2012
 * Descripción:Se agrego el campo de fechaingresoEmpresa para conocer la 1ra
 * fecha en la que ingreso a la empresa.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:30/07/2012 Descripción:Se agrego el
 * limite a los campos del RFC, CURP, IMSS
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Fecha:21/01/2013 Descripción:Se cambio el
 * campo Apodo por NombreAbreviado, tambien se corrigio este dato
 * estadoNacionalidad por estadoNacimiento.
 * -----------------------------------------------------------------------------
 * Clave:DRO01 Autor:Dayane Rocha Fecha:01/07/2013 Descripción:Se agrego campo :
 * telefono
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "Empleados",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales_ID", "clave"})})
public class Empleados implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clave;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreAbreviado;//JSA03
    @Column(length = 60000, nullable = true)
    private byte[] foto;
    private String domicilio;
    private String colonia;
    private String numeroExt;
    private String numeroInt;
    @ManyToOne
    private Cp cp;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @ManyToOne
    private Genero genero;
    @ManyToOne
    private Ciudades ciudades;
    @ManyToOne
    private Municipios municipios;
    @ManyToOne
    private Estados estados;
    @ManyToOne
    private Paises paises;

    @ManyToOne
    private Paises paisOrigen;
    private String nacionalidad;
    @ManyToOne
    private Estados estadoNacimiento;
    private String lugarNacimiento;
    @Column(length = 15)//JSA02
    private String RFC;
    @Column(length = 20)//JSA02
    private String CURP;
    @Column(length = 15)//JSA02
    private String IMSS;
    private String clinicaIMSS;
    private Integer estadoCivil;
    private boolean status;
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoEmpresa;//JSA01
    private String telefono; //DRO01   
    private String correoElectronico;

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public String getIMSS() {
        return IMSS;
    }

    public void setIMSS(String IMSS) {
        this.IMSS = IMSS;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClinicaIMSS() {
        return clinicaIMSS;
    }

    public void setClinicaIMSS(String clinicaIMSS) {
        this.clinicaIMSS = clinicaIMSS;
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Integer estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Estados getEstadoNacimiento() {
        return estadoNacimiento;
    }

    public void setEstadoNacimiento(Estados estadoNacimiento) {
        this.estadoNacimiento = estadoNacimiento;
    }

    public String getNombreAbreviado() {
        return nombreAbreviado;
    }

    public void setNombreAbreviado(String nombreAbreviado) {
        this.nombreAbreviado = nombreAbreviado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroExt() {
        return numeroExt;
    }

    public void setNumeroExt(String numeroExt) {
        this.numeroExt = numeroExt;
    }

    public String getNumeroInt() {
        return numeroInt;
    }

    public void setNumeroInt(String numeroInt) {
        this.numeroInt = numeroInt;
    }

    public Paises getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(Paises paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public Date getFechaIngresoEmpresa() {
        return fechaIngresoEmpresa;
    }

    public void setFechaIngresoEmpresa(Date fechaIngresoEmpresa) {
        this.fechaIngresoEmpresa = fechaIngresoEmpresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
