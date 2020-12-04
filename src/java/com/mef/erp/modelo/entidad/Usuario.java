/**
 * @author: Victor Lopez
 * Fecha de Creación: 15/04/2011
 * Compañía: MacroPro.
 * Descripción del programa: Entidad de Usuario para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando   
 * Fecha:19-10-2011
 * Descripción:Se agrego la propiedad unique = true a la clave para que no sea duplicada. 
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.hibernate.annotations.IndexColumn;


@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 30, nullable = false, unique = true)//JSA01)
    private String clave;
    @Column(nullable = false)
    private String nombre;
    @Column(length = 40, nullable = false)
    private String password;
    @ManyToOne
    private Perfiles perfiles;
    @Column(length = 30)
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaExpiracion;
    private Boolean activaFechaEx;
    private Boolean restringeIP;
    private Boolean restringeDominio;
    private Boolean restringeSubRed;
    @OneToMany(fetch = FetchType.EAGER)
    @IndexColumn(name="id")
    private List<Restriccion> restricciones;
//    private String claveRazonSocial;
    private Integer idioma;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Perfiles getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(Perfiles perfiles) {
        this.perfiles = perfiles;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActivaFechaEx() {
        return activaFechaEx;
    }

    public void setActivaFechaEx(Boolean activaFechaEx) {
        this.activaFechaEx = activaFechaEx;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public List<Restriccion> getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(List<Restriccion> restricciones) {
        this.restricciones = restricciones;
    }

    public Boolean getRestringeDominio() {
        return restringeDominio;
    }

    public void setRestringeDominio(Boolean restringeDominio) {
        this.restringeDominio = restringeDominio;
    }

    public Boolean getRestringeIP() {
        return restringeIP;
    }

    public void setRestringeIP(Boolean restringeIP) {
        this.restringeIP = restringeIP;
    }

    public Boolean getRestringeSubRed() {
        return restringeSubRed;
    }

    public void setRestringeSubRed(Boolean restringeSubRed) {
        this.restringeSubRed = restringeSubRed;
    }

//    public String getClaveRazonSocial() {
//        return claveRazonSocial;
//    }
//
//    public void setClaveRazonSocial(String claveRazonSocial) {
//        this.claveRazonSocial = claveRazonSocial;
//    }

    public Integer getIdioma() {
        return idioma;
    }

    public void setIdioma(Integer idioma) {
        this.idioma = idioma;
    }
    

}
