/**
 * @author: Victor Lopez
 * Fecha de Creación: 09/12/2011
 * Compañía: Macropro.
 * Descripción del programa: interface ConfiguraMovimientoDAO para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Armando Sanchez
 * Fecha:09/11/2012
 * Descripción:Se agrego el metodo para obtener los movimientos por un contenedor
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ConfiguraMovimiento;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;
import java.util.List;

public interface ConfiguraMovimientoDAOIF {

    Mensaje agregar(ConfiguraMovimiento entity, String uuidCxn);

    Mensaje actualizar(ConfiguraMovimiento entity, String uuidCxn);

    Mensaje eliminar(ConfiguraMovimiento entity, String uuidCxn);

    Mensaje getConfiguraMovimientoAll(String claveRazonesSocial, String uuidCxn);

//////    ConfiguraMovimiento getConfiguraMovimientoPorClave(String clave, String claveRazonesSocial, String uuidCxn);
//////
//////    ConfiguraMovimiento getConfiguraMovimientoPorClaveYRazon(String clave, String claveRazon, String uuidCxn);

    Mensaje consultaPorRangosConfigMov(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje saveDeleteConfiguraMovimiento(List<ConfiguraMovimiento> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

    Mensaje PorGrupoMenu(String idContenedor, String claveRazonSocial, String uuidCxn);//JSA01
    
    Mensaje buscaPorIdYRazonSocial(Long id, String claveRazonSocial, String uuidCxn);
    
    Mensaje buscaConfiguracionMovimSistema(Long id, String uuidCxn);
    
    Mensaje getAllConfiguracionMovimSistema(String uuidCxn);
}
