/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 04/08/2011 Compañía:
 * Exito Software. Descripción del programa: interface para servicio de
 * configuracion de mascaras
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:18/02/2015 Descripción: Se le agrego
 * al metodo getSaveMascarasAfectaClaves el generar el archivo de properties de las mascaras.
 * Se agrego el metodo getFilePropertiesMascara para obtener el nombre del properties de las mascara
 * por base de datos ya que se puede tener varias bds en el mismo servidor.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mascaras;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioConfigMascarasIF {

    /*Mascaras*/
    Mensaje agregar(Mascaras entity, String uuidCxn);

    /*boolean*/
    Mensaje actualizar(Mascaras entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(Mascaras entity, String uuidCxn);

    /*List<Mascaras>*/
    Mensaje getConfigMascarasAll(String uuidCxn);

    /*Mascaras*/
    Mensaje getConfigMascarasPorClave(String clave, String uuidCxn);

    /*Mensaje*/
    Mensaje getSaveMascarasAfectaClaves(List<Mascaras> listMascaras, boolean soloAplicarReEstructuracion, byte[] propertiesMascara, String ubicacion, String uuidCxn, String uuidCxnMaestra);//JSA03

    /*List<Mascaras>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*Properties*/
    Mensaje getFilePropertiesMascara(String directorioReportesDelSistema, String uuidCxn);//JSA03
    
}
