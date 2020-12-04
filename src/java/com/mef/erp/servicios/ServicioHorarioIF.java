/**
 * @author: Victor Lopez
 * Fecha de Creación: 29/08/2011
 * Compañía: Macropro.
 * Descripción del programa: Interface para servicio Horario
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: 
 * Autor: 
 * Fecha: 
 * Descripción: 
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Horario;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;

public interface ServicioHorarioIF {

    /*Horario*/
    Mensaje agregar(Horario entity , String uuidCxn);

    /*boolean*/
    Mensaje actualizar(Horario entity , String uuidCxn);

    /*boolean*/
    Mensaje eliminar(Horario entity , String uuidCxn);

    /*List<Horario>*/
    Mensaje getHorarioAll(String uuidCxn);

    /*Horario*/
    Mensaje getHorarioPorClave(String clave , String uuidCxn);

    /*List<Horario>*/
    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores , String uuidCxn);

    /*List<Horario>*/
    public Mensaje consultaPorRangos(Integer inicio, Integer rango , String uuidCxn);

    /*Boolean*/
    public Mensaje existeDato(String campo, Object valor , String uuidCxn);

    /*List<Horario>*/
    public Mensaje getHorariosPorRazonSocial(RazonesSociales razonSocial , String uuidCxn);
    
    
}
