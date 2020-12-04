/**
 * @author: Victor Lopez Fecha de Creación:17/05/2016 Compañía: Macropro.
 * Descripción del programa: interfaz VacacionesDisfrutadasDAOIF para llamados a
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
import com.mef.erp.modelo.entidad.VacacionesDisfrutadas;
import java.util.List;

public interface VacacionesDisfrutadasDAOIF {

    Mensaje agregar(VacacionesDisfrutadas entity, String uuidCxn);

    Mensaje actualizar(VacacionesDisfrutadas entity, String uuidCxn);

    Mensaje eliminar(VacacionesDisfrutadas entity, String uuidCxn);

    Mensaje getVacacionesDisfrutadasAll(String claveRazonesSocial, String uuidCxn);

    Mensaje getVacacionesPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn);

    Mensaje saveDeleteVacacionesDisfrutadas(List<VacacionesDisfrutadas> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
    
    Mensaje ObtenerVacacionesDisfruradasMaxima(String claveEmpleado,String claveRazonSocial, String uuidCxn);
    
    Mensaje EliminarVacacionesDisfrutadas(List<VacacionesAplicacion> vacAplicacion, String uuidCxn);

}
