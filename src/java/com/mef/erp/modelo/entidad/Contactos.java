/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mef.erp.modelo.entidad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 *
 * @author daniel
 */
@Entity
public class Contactos implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 255, nullable = false)
    private String nombre;
    private String puesto;
    private String telefono;
    private String extencion_telefono;
    private String movil;
    private String email;
    private String fax;
    private String extencion_fax;

    @ManyToOne
    private Bancos bancos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getExtencion_telefono() {
        return extencion_telefono;
    }
    public void setExtencion_telefono(String extencion_telefono) {
        this.extencion_telefono = extencion_telefono;
    }

    public String getMovil() {
        return movil;
    }
    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getExtencion_fax() {
        return extencion_fax;
    }
    public void setExtencion_fax(String extencion_fax) {
        this.extencion_fax = extencion_fax;
    }

    public Bancos getBancos() {
        return bancos;
    }
    public void setBancos(Bancos bancos) {
        this.bancos = bancos;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
