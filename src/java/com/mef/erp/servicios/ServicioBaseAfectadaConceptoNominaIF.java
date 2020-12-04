/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: interface servicio tabla BaseAfectadaConceptoNomina
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

import com.mef.erp.modelo.entidad.BaseAfecConcepNom;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioBaseAfectadaConceptoNominaIF {

    /*BaseAfecConcepNom*/
    Mensaje agregar(BaseAfecConcepNom entity, String uuidCxn);//JSA01

    /*boolean*/
    Mensaje actualizar(BaseAfecConcepNom entity, String uuidCxn);//JSA01

    /*boolean*/
    Mensaje eliminar(BaseAfecConcepNom entity, String uuidCxn);//JSA01

    /*List<BaseAfecConcepNom>*/
    Mensaje getBaseAfectadaConceptoNominaAll(String uuidCxn);//JSA01

    /*BaseAfecConcepNom*/
    Mensaje getBaseAfectadaConceptoNominaPorClave(String clave, String uuidCxn);//JSA01

    /*List<BaseAfecConcepNom>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    /*List<BaseAfecConcepNom>*/
    Mensaje agregarListaBaseAfectadaConceptoNomina(List<BaseAfecConcepNom> entitys, int rango, String uuidCxn);//JSA01

    /*Boolean*/
    Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn);//JSA01

}
