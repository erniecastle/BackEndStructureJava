/**
 * @author: Victor Lopez Fecha de Creación: 09/12/2011 Compañía: Macropro.
 * Descripción del programa: Interface para ConfiguraMovimiento
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Armando Sanchez Fecha:09/11/2012 Descripción:Se agrego el
 * metodo para obtener los movimientos por un contenedor
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ConfiguraMovimiento;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import java.util.List;

public interface ServicioConfiguraMovimientoIF {

    /*ConfiguraMovimiento*/
    Mensaje agregar(ConfiguraMovimiento entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(ConfiguraMovimiento entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(ConfiguraMovimiento entity, String uuidCxn);

    /*List<ConfiguraMovimiento> */
    Mensaje getConfiguraMovimientoAll(String claveRazonesSocial, String uuidCxn);

    /*List<ConfiguraMovimiento> */
    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<ConfiguraMovimiento>*/
    Mensaje saveDeleteConfiguraMovimiento(List<ConfiguraMovimiento> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    /*List<ConfiguraMovimiento>*/
    Mensaje PorGrupoMenu(String idContenedor, String claveRazonSocial, String uuidCxn);//JSA01

    /*ConfiguraMovimiento*/
    Mensaje buscaPorIdYRazonSocial(Long id, String claveRazonSocial, String uuidCxn);

    /*ConfiguraMovimiento*/
    Mensaje buscaConfiguracionMovimSistema(Long id, String uuidCxn);

    /*List<ConfiguraMovimiento>*/
    Mensaje getAllConfiguracionMovimSistema(String uuidCxn);

}
