/**
 * @author: Ernesto Fecha de Creación: 07/08/2013 Compañía: Exito Descripción
 * del programa: clase servicio para Firmas
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:DRO01 Autor:Dayane Rocha. Fecha:23/08/2013 Descripción: Se agregaron
 * servicios para Firmas.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.FirmasDAO;
import com.mef.erp.modelo.entidad.Firmas;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public class ServicioFirmas implements ServicioFirmasIF {

    private FirmasDAO firmasDAO;
   

    public Mensaje getFirmasPorFechaYRazonSocial(String razonSocial, Date fecha, String uuidCxn) {
        return getFirmasDAO().getFirmasPorFechaYRazonSocial(razonSocial, fecha, uuidCxn);
    }

    public Mensaje agregar(Firmas entity, String uuidCxn) {//DRO01
        return getFirmasDAO().agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Firmas entity, String uuidCxn) {//DRO01
        return getFirmasDAO().actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Firmas entity, String uuidCxn) {//DRO01
        return getFirmasDAO().eliminar(entity, uuidCxn);
    }

    public Mensaje getFirmasAll(String uuidCxn) {//DRO01
        return getFirmasDAO().getFirmasAll(uuidCxn);
    }

    public Mensaje getFirmasPorClave(String clave, String uuidCxn) {//DRO01
        return getFirmasDAO().getFirmasPorClave(clave, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//DRO01
        return getFirmasDAO().existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteFirmas(List<Firmas> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {//DRO01
        return getFirmasDAO().saveDeleteFirmas(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltrosFirmas(String query, Object[] campos, Object[] valores, String uuidCxn) {//DRO01
        return getFirmasDAO().consultaPorFiltrosFirmas(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangosFirmas(Integer inicio, Integer rango, String uuidCxn) {//DRO01
        return getFirmasDAO().consultaPorRangosFirmas(inicio, rango, uuidCxn);
    }

    public FirmasDAO getFirmasDAO() {
        return firmasDAO;
    }

    public void setFirmasDAO(FirmasDAO firmasDAO) {

        this.firmasDAO = firmasDAO;
    }

}
