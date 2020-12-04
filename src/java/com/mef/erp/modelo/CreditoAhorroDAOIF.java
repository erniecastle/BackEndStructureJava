/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 16/08/2011 Compañía:
 * Exito Software. Descripción del programa: inteface de clase de CreditoAhorro
 * DAO para llamados a metodos de HIBERNATE
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

import com.mef.erp.modelo.entidad.CreditoAhorro;
import com.mef.erp.modelo.entidad.Mensaje;

public interface CreditoAhorroDAOIF extends GenericDAO<CreditoAhorro, Long> {

    Mensaje agregar(CreditoAhorro entity,String uuidCxn);//JSA01

    Mensaje actualizar(CreditoAhorro entity,String uuidCxn);//JSA01

    Mensaje eliminar(CreditoAhorro entity,String uuidCxn);//JSA01

    Mensaje getCreditoAhorroAll(String claveRazonesSociales, String tipoConfiguracion,String uuidCxn);//JSA01

    Mensaje getCreditoAhorroPorClave(String clave, String claveRazonesSociales, String tipoConfiguracion,String uuidCxn);//JSA01
}
