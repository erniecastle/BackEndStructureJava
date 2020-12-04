/**
 * @author: Victor Lopez Fecha de Creación: 17/05/2016 Compañía: Macropro.
 * Descripción del programa: interfaz VacacionesAplicacionDAOIF para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.VacacionesAplicacion;
import java.util.List;

public interface VacacionesAplicacionDAOIF {

    Mensaje agregar(VacacionesAplicacion entity, String uuidCxn);

    Mensaje actualizar(VacacionesAplicacion entity, String uuidCxn);

    Mensaje eliminar(VacacionesAplicacion entity, String uuidCxn);
    
    Mensaje getVacacionesAplicacionAll(String claveRazonesSocial, String uuidCxn);
    
    Mensaje getVacacionesPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn);

    Mensaje saveDeleteVacacionesAplicacion(List<VacacionesAplicacion> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
