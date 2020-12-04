/**
 * @author: Ernesto castillo
 * Fecha de Creación: 27/05/2011
 * Compañía: Exito Software.
 * Descripción del programa: inteface de clase de Estados para llamados a 
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01
 * Autor: Abraham Daniel Arjona Peraza
 * Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:10/11/2011
 * Descripción:Se cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Municipios;
import java.util.List;

public interface MunicipiosDAOIF extends GenericDAO<Municipios, String> {//AAP01

    Mensaje getMunicipiosAll(String uuidCxn);

    Mensaje getMunicipiosPorClave(String clave, String uuidCxn);//AAP01

    Mensaje agregar(Municipios entity, String uuidCxn);//JSA01

    Mensaje actualizar(Municipios entity, String uuidCxn);//JSA01

    Mensaje eliminar(Municipios entity, String uuidCxn);//JSA01

    Mensaje getMunicipiosPorEstado(String claveEstado, String uuidCxn);
    
    Mensaje getMunicipiosPorPais(String clavePais, String uuidCxn);

    Mensaje consultaPorFiltrosMunicipio(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje saveDeleteMunicipios(List<Municipios> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
