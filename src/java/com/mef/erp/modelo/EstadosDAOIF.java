/**
 * @author: Ernesto castillo Fecha de Creación: 27/05/2011 Compañía: Exito
 * Software. Descripción del programa: inteface de clase de Estados para
 * llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Estados;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface EstadosDAOIF extends GenericDAO<Estados, String> {//AAP01

    public Mensaje agregar(Estados entity, String uuidCxn);//JSA01

    public Mensaje actualizar(Estados entity, String uuidCxn);//JSA01

    public Mensaje eliminar(Estados entity, String uuidCxn);//JSA01

    public Mensaje getEstadosAll(String uuidCxn);

    public Mensaje getEstadosPorClave(String clave, String uuidCxn);//AAP01

    public Mensaje getEstadosPorPais(String clavePais, String uuidCxn);

    public Mensaje consultaPorFiltrosEstado(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);//JSA01

    public Mensaje consultaPorRangos(Integer inicio, Integer rango , String uuidCxn);//JSA01

    public Mensaje existeDato(String campo, Object valor , String uuidCxn);//JSA01

    public Mensaje saveDeleteEstados(List<Estados> entitysCambios, Object[] clavesDelete, int rango , String uuidCxn);//JSA01
}
