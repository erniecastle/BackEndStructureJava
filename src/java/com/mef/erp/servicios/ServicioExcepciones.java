/**
 * @author: Ernesto Castillo Fecha de Creación: 07/12/2011 Compañía: Exito
 * Software. Descripción del programa: clase para servicio Excepciones
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ExcepcionesDAO;
import com.mef.erp.modelo.entidad.Excepciones;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioExcepciones implements ServicioExcepcionesIF {

    private ExcepcionesDAO excepcionesDAO;

  

    public Mensaje agregar(Excepciones entity, String uuidCxn) {
        return getExcepcionesDAO().agregar(entity, uuidCxn);
    }

    public Mensaje agregarExcepciones(List<Excepciones> entitysCambios, String uuidCxn) {
        return getExcepcionesDAO().agregarExcepciones(entitysCambios, uuidCxn);
    }

    public Mensaje getExcepcionesAll(String uuidCxn) {
        return getExcepcionesDAO().getExcepcionesAll(uuidCxn);
    }

    public ExcepcionesDAO getExcepcionesDAO() {
        return excepcionesDAO;
    }

    public void setExcepcionesDAO(ExcepcionesDAO excepcionesDAO) {
        this.excepcionesDAO = excepcionesDAO;
    }
}
