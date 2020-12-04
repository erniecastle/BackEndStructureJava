/**
 * @author: Ernesto castillo
 * Fecha de Creación: 15/03/2011
 * Compañía: Exito Software.
 * Descripción del programa: inteface de clase de Paises para llamados a 
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01
 * Autor: Abraham Daniel Arjona Peraza
 * Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Paises;
import java.util.List;

public interface PaisesDAOIF extends GenericDAO<Paises, String> {//AAP01

    Mensaje agregar(Paises entity, String uuidCxn);

    public Mensaje actualizar(Paises entity, String uuidCxn);

    public Mensaje eliminar(Paises entity, String uuidCxn);

    Mensaje getPaisAll(String uuidCxn);

    Mensaje getPaisPorClave(String clave,String uuidCxn);//AAP01

    public Mensaje consultaPorFiltrosPais(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango,String uuidCxn);

    public Mensaje consultaPorRangosPais(Integer inicio, Integer rango,String uuidCxn);

    public Mensaje existeDato(String campo, Object valor,String uuidCxn);

    public Mensaje saveDeletePaises(List<Paises> entitysCambios, Object[] clavesDelete, int rango,String uuidCxn);

}
