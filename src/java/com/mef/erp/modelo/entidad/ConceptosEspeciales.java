/**
 * @author: Jose Armando
 * Fecha de Creación: 26/08/2012
 * Compañía: Macropro
 * Descripción del programa: Conceptos especiales se creo con el fin de definir cuales
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ConceptosEspeciales implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private TipoConceptosEspeciales tipoConceptosEspeciales;
    @ManyToOne
    private ConceptoDeNomina conceptoDeNomina;

    public ConceptoDeNomina getConceptoDeNomina() {
        return conceptoDeNomina;
    }

    public void setConceptoDeNomina(ConceptoDeNomina conceptoDeNomina) {
        this.conceptoDeNomina = conceptoDeNomina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoConceptosEspeciales getTipoConceptosEspeciales() {
        return tipoConceptosEspeciales;
    }

    public void setTipoConceptosEspeciales(TipoConceptosEspeciales tipoConceptosEspeciales) {
        this.tipoConceptosEspeciales = tipoConceptosEspeciales;
    }
}
