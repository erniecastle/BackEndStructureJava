/**
 * @author: Victor Lopez Fecha de Creación: 06/09/2011 Compañía: Macropro.
 * Descripción del programa: inteface de clase para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.DatosTipoValor;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoDeRedondeo;
import com.mef.erp.modelo.entidad.TipoDeValor;
import java.util.List;

public interface TipoDeRedondeoDAOIF {

    Mensaje getTipoDeRedondeoAll(String uuidCxn);//JSA01

    Mensaje getTipoDeRedondeoPorClave(String clave, String uuidCxn);//JSA01

    Mensaje agregar(TipoDeRedondeo entity, String uuidCxn);//JSA01

    Mensaje actualizar(TipoDeRedondeo entity, String uuidCxn);//JSA01

    Mensaje eliminar(TipoDeRedondeo entity, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosTipoRedondeo(String query, Object[] campos, Object[] valores, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje agregarListTipoDeRedondeo(List<TipoDeRedondeo> entitys, int rango, String uuidCxn);//JSA01

    Mensaje deleteListQuerys(String tabla, String campo, Object[] valores, String uuidCxn);//JSA01

    Mensaje getTipoDeRedondeoPorTipoValor(TipoDeValor tipoValor, String uuidCxn);//JSA01

    Mensaje updateTipoRedondeoValor(TipoDeRedondeo entity, List<DatosTipoValor> eliminaDatosTipoValores, String uuidCxn);//JSA01
}
