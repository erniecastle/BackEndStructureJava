/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: interface servicio tabla BaseAfectadaGrupo
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.BaseAfectadaGrupo;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioBaseAfectadaGrupoIF {

    /*BaseAfectadaGrupo*/
    Mensaje agregar(BaseAfectadaGrupo entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(BaseAfectadaGrupo entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(BaseAfectadaGrupo entity, String uuidCxn);

    /*List<BaseAfectadaGrupo>*/
    Mensaje getBaseAfectadaGrupoAll(String uuidCxn);

    /*BaseAfectadaGrupo*/
    Mensaje getBaseAfectadaGrupoPorClave(String clave, String uuidCxn);

    /*List<BaseAfectadaGrupo>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<BaseAfectadaGrupo>*/
    Mensaje agregarListaBaseAfectadaGrupo(List<BaseAfectadaGrupo> entitys, int rango, String uuidCxn);

    /*Boolean*/
    Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn);
}
