/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 16/06/2011 Compañía:
 * Exito Software. Descripción del programa: clase SERVICIO, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.PeriodicidadDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Periodicidad;
import java.util.List;

public class ServicioPeriodicidad implements ServicioPeriodicidadIF {

    private PeriodicidadDAO periodicidadDAO;

    public Mensaje agregar(Periodicidad entity, String uuidCxn) {//JSA01
        return periodicidadDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Periodicidad entity, String uuidCxn) {//JSA01
        return periodicidadDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Periodicidad entity, String uuidCxn) {//JSA01
        return periodicidadDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getPeriodicidadAll(String uuidCxn) {//JSA01
        return periodicidadDAO.getPeriodicidadAll(uuidCxn);
    }

    public Mensaje getPeriodicidadPorClave(String clave, String uuidCxn) {//JSA01
        return periodicidadDAO.getPeriodicidadPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return periodicidadDAO.consultaPorFiltrosPeriodicidad(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return periodicidadDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return periodicidadDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeletePeriodicidad(List<Periodicidad> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return periodicidadDAO.saveDeletePeriodicidad(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public PeriodicidadDAO getPeriodicidadDAO() {
        return periodicidadDAO;
    }

    public void setPeriodicidadDAO(PeriodicidadDAO periodicidadDAO) {
        this.periodicidadDAO = periodicidadDAO;
    }
}
