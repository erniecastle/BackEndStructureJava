/**
 * @author: Victor Lopez
 * Fecha de Creación: 30/08/2011
 * Compañía: MacroPro
 * Descripción del programa: inteface de clase de Horario para llamados a 
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:10/11/2011
 * Descripción:Se cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Horario;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;

public interface HorarioDAOIF extends GenericDAO<Horario, Long> {


    Mensaje agregar(Horario entity , String uuidCxn);//JSA01

    Mensaje actualizar(Horario entity , String uuidCxn);//JSA01

    Mensaje eliminar(Horario entity , String uuidCxn);//JSA01

    Mensaje getHorarioAll(String uuidCxn);//JSA01

    Mensaje getHorarioPorClave(String clave , String uuidCxn);//JSA01

    public Mensaje consultaPorFiltrosHorario(String query, Object[] campos, Object[] valores , String uuidCxn);//JSA01

    public Mensaje consultaPorRangos(Integer inicio, Integer rango , String uuidCxn);//JSA01

    public Mensaje existeDato(String campo, Object valor , String uuidCxn);//JSA01

    public Mensaje getHorariosPorRazonSocial(RazonesSociales razonSocial , String uuidCxn);//JSA01
}
