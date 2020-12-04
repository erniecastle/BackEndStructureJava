/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 04/08/2011 Compañía:
 * Exito Software. Descripción del programa: Entidad de Mascaras para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:08/01/2014 Descripción:Se agregon los
 * campos permitirModificarMascara y mensaje. El permitirModificarMascara es
 * para indicar que algunas mascaras no se modificaran de manera simple, es
 * decir, se tienen que hacer ciertos cambios en el compilar. El campo mensaje
 * es solo para cuando se modifiquen las claves y durante ese proceso hubiera un
 * error regresarlo arriba con ese campo.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:30/04/2015 Descripción: Se agrego el
 * campo mascaraOriginal para cuando marca un error revertir el cambio.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Mascaras implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @Column(length = 255, nullable = true)
    private String descripcion;
    @Column(length = 30, nullable = false)
    private String mascara;
    @Column(length = 1, nullable = false)
    private String caracterMarcador;
    @Column(length = 5, nullable = false)
    private String tipoMascara;
    @Transient
    private String message;//JSA01
    private Boolean permitirModificarMascara;//JSA01
    private Boolean definirCaracterMarcador;
    @Transient
    private String mascaraOriginal;//JSA02

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public String getCaracterMarcador() {
        return caracterMarcador;
    }

    public void setCaracterMarcador(String caracterMarcador) {
        this.caracterMarcador = caracterMarcador;
    }

    public String getTipoMascara() {
        return tipoMascara;
    }

    public void setTipoMascara(String tipoMascara) {
        this.tipoMascara = tipoMascara;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isPermitirModificarMascara() {
        return permitirModificarMascara;
    }

    public void setPermitirModificarMascara(Boolean permitirModificarMascara) {
        this.permitirModificarMascara = permitirModificarMascara;
    }

    public Boolean isDefinirCaracterMarcador() {
        return definirCaracterMarcador;
    }

    public void setDefinirCaracterMarcador(Boolean definirCaracterMarcador) {
        this.definirCaracterMarcador = definirCaracterMarcador;
    }

    public String getMascaraOriginal() {
        return mascaraOriginal;
    }

    public void setMascaraOriginal(String mascaraOriginal) {
        this.mascaraOriginal = mascaraOriginal;
    }
}
