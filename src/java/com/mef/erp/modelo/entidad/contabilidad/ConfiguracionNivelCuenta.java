/**
 * @author: Ernesto Castillo Fecha de Creación: 23/11/2015 Compañía: Exito
 * Software. Descripción del programa: Entidad de ConfiguracionNivelCuenta para
 * Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad.contabilidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ernesto
 */
@Entity
public class ConfiguracionNivelCuenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean activarCxnContable;
    private String rutaArchivoPolizas;
    private Integer orgPoliza;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "configuracionNivelCuenta")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<EstrucCuenta> estrucCuenta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isActivarCxnContable() {
        return activarCxnContable;
    }

    public void setActivarCxnContable(Boolean activarCxnContable) {
        this.activarCxnContable = activarCxnContable;
    }

    public String getRutaArchivoPolizas() {
        return rutaArchivoPolizas;
    }

    public void setRutaArchivoPolizas(String rutaArchivoPolizas) {
        this.rutaArchivoPolizas = rutaArchivoPolizas;
    }

    public Integer getOrgPoliza() {
        return orgPoliza;
    }

    public void setOrgPoliza(Integer orgPoliza) {
        this.orgPoliza = orgPoliza;
    }

    public List<EstrucCuenta> getEstrucCuenta() {
        return estrucCuenta;
    }

    public void setEstrucCuenta(List<EstrucCuenta> estrucCuenta) {
        this.estrucCuenta = estrucCuenta;
    }

}
