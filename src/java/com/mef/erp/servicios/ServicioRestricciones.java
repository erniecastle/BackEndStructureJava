/**
 * @author: Ernesto Castillo Fecha de Creación: 30/06/2011 Compañía: Exito
 * Software. Descripción del programa: clase para servicio Restricciones
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

import com.mef.erp.modelo.RestriccionesDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Restriccion;
import com.mef.erp.modelo.entidad.Usuario;
import java.util.List;

public class ServicioRestricciones implements ServicioRestriccionesIF {

    private RestriccionesDAO restriccionesDAO;

    public Mensaje agregar(Restriccion entity, String uuidCxnMaestra) {//JSA01
        return restriccionesDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(Restriccion entity, String uuidCxnMaestra) {//JSA01
        return restriccionesDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(Restriccion entity, String uuidCxnMaestra) {//JSA01
        return restriccionesDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje getRestriccionesAll(String uuidCxnMaestra) {//JSA01
        return restriccionesDAO.getRestriccionesAll(uuidCxnMaestra);
    }

    public Mensaje getRestriccionesPorClave(String clave, String uuidCxnMaestra) {//JSA01
        return restriccionesDAO.getRestriccionesPorClave(clave, uuidCxnMaestra);
    }

    public Mensaje agregarListaRestricciones(List<Restriccion> entitys, int rango, String uuidCxnMaestra) {//JSA01
        return restriccionesDAO.agregarListaRestricciones(entitys, rango, uuidCxnMaestra);
    }

    public Mensaje deleteListaRestricciones(List<Restriccion> entitys, Usuario user, int rango, String uuidCxnMaestra) {//JSA01
        return restriccionesDAO.deleteListaRestricciones(entitys, user, rango, uuidCxnMaestra);
    }

    public RestriccionesDAO getRestriccionesDAO() {
        return restriccionesDAO;
    }

    public void setRestriccionesDAO(RestriccionesDAO restriccionesDAO) {
        this.restriccionesDAO = restriccionesDAO;
    }
}
