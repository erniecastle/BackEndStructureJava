/**
 * @author: Ernesto Valenzuela Fecha de Creación: 15/03/2011 Compañía: Exito
 * Software. Descripción del programa: clase SERVICIO ciudades, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 21-07-2011
 * Descripción: Se cambio el tipo de dato de los metodos de eliminar y
 * actualizar.
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 29/07/2011
 * Descripción: Se cambió a String la clave de ciudad
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.SemaforoTimbradoPacDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.SemaforoTimbradoPac;

public class ServicioSemaforoTimbradoPac implements ServicioSemaforoTimbradoPacIF {

    private SemaforoTimbradoPacDAO semaforoTimbradoPacDAO;

    public Mensaje agregar(SemaforoTimbradoPac entity, String uuidCxn) {
        return semaforoTimbradoPacDAO.agregar(entity, uuidCxn);
    }

    public Mensaje eliminar(SemaforoTimbradoPac entity, String uuidCxn) {
        return semaforoTimbradoPacDAO.eliminar(entity, uuidCxn);
    }

    public SemaforoTimbradoPacDAO getSemaforoTimbradoPacDAO() {
        return semaforoTimbradoPacDAO;
    }

    public void setSemaforoTimbradoPacDAO(SemaforoTimbradoPacDAO semaforoTimbradoPacDAO) {
        this.semaforoTimbradoPacDAO = semaforoTimbradoPacDAO;
    }
}
