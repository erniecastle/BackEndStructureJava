/**
 * @author: Victor Lopez Fecha de Creación: 15/06/2011 Compañía: MacroPro.
 * Descripción del programa: inteface de clase de Restricciones para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Restriccion;
import com.mef.erp.modelo.entidad.Usuario;
import java.util.List;

public interface RestriccionesDAOIF extends GenericDAO<Restriccion, Long> {

    Mensaje agregar(Restriccion entity, String uuidCxnMaestra);//JSA01

    Mensaje actualizar(Restriccion entity, String uuidCxnMaestra);//JSA01

    Mensaje eliminar(Restriccion entity, String uuidCxnMaestra);//JSA01

    Mensaje getRestriccionesAll(String uuidCxnMaestra);//JSA01

    Mensaje getRestriccionesPorClave(String clave, String uuidCxnMaestra);//JSA01

    public Mensaje agregarListaRestricciones(List<Restriccion> entitys, int rango, String uuidCxnMaestra);//JSA01

    public Mensaje deleteListaRestricciones(List<Restriccion> entitys, Usuario user, int rango, String uuidCxnMaestra);//JSA01
}
