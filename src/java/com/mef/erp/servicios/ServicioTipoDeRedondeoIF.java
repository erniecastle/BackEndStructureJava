/**
 * @author: Victor Lopez Fecha de Creación: 06/09/2011 Compañía: Macropro.
 * Descripción del programa: Interfaface para servicio de tipos de redondeo
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.DatosTipoValor;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoDeRedondeo;
import com.mef.erp.modelo.entidad.TipoDeValor;
import java.util.List;

public interface ServicioTipoDeRedondeoIF {

    /*List<TipoDeRedondeo>*/
    Mensaje getTipoDeRedondeoAll(String uuidCxn);

    /*TipoDeRedondeo*/
    Mensaje getTipoDeRedondeoPorClave(String clave, String uuidCxn);

    /*TipoDeRedondeo*/
    Mensaje agregar(TipoDeRedondeo entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(TipoDeRedondeo entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(TipoDeRedondeo entity, String uuidCxn);

    /*List<TipoDeRedondeo>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<TipoDeRedondeo>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<TipoDeRedondeo>*/
    Mensaje agregarListTipoDeRedondeo(List<TipoDeRedondeo> entitys, int rango, String uuidCxn);

    /*Boolean*/
    Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn);

    /*List<TipoDeRedondeo>*/
    Mensaje getTipoDeRedondeoPorTipoValor(TipoDeValor tipoValor, String uuidCxn);

    /*Object*/
    Mensaje updateTipoRedondeoValor(TipoDeRedondeo entity, List<DatosTipoValor> eliminaDatosTipoValores, String uuidCxn);
}
