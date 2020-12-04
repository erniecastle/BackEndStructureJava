/**
 * @author: Ernesto Castillo Fecha de Creación: 13/05/2016 Compañía: Exito
 * Software. Descripción del programa: Entidad de Vacaciones Aplicacion para
 * Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ernesto
 */
@Entity
public class VacacionesAplicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private VacacionesDevengadas vacacionesDevengadas;
    @ManyToOne(cascade = CascadeType.ALL)
    private VacacionesDisfrutadas vacacionesDisfrutadas;
    private Integer diasVac;
    private Double diasPrima;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VacacionesDevengadas getVacacionesDevengadas() {
        return vacacionesDevengadas;
    }

    public void setVacacionesDevengadas(VacacionesDevengadas vacacionesDevengadas) {
        this.vacacionesDevengadas = vacacionesDevengadas;
    }

    public VacacionesDisfrutadas getVacacionesDisfrutadas() {
        return vacacionesDisfrutadas;
    }

    public void setVacacionesDisfrutadas(VacacionesDisfrutadas vacacionesDisfrutadas) {
        this.vacacionesDisfrutadas = vacacionesDisfrutadas;
    }

    public Integer getDiasVac() {
        return diasVac;
    }

    public void setDiasVac(Integer diasVac) {
        this.diasVac = diasVac;
    }

    public Double getDiasPrima() {
        return diasPrima;
    }

    public void setDiasPrima(Double diasPrima) {
        this.diasPrima = diasPrima;
    }

}
