/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 04/08/2011 Compañía:
 * Exito Software. Descripción del programa: inteface de clase de Configuracion
 * de mascaras para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:08/10/2014 Descripción:Se
 * agrego el el metodo getSaveMascarasAfectaClaves para reEstructurar las claves
 * asi como tmb las tablas datos.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:18/02/2015 Descripción: Se le agrego
 * al metodo getSaveMascarasAfectaClaves el generar el archivo de properties de las mascaras.
 * Se agrego el metodo getFilePropertiesMascara para obtener el nombre del properties de las mascara
 * por base de datos ya que se puede tener varias bds en el mismo servidor.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mascaras;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ConfigMascarasDAOIF {

    Mensaje agregar(Mascaras entity, String uuidCxn);//JSA01

    Mensaje actualizar(Mascaras entity, String uuidCxn);//JSA01

    Mensaje eliminar(Mascaras entity, String uuidCxn);//JSA01

    Mensaje getConfigMascarasAll(String uuidCxn);//JSA01

    Mensaje getConfigMascarasPorClave(String clave, String uuidCxn);//JSA01

    Mensaje getSaveMascarasAfectaClaves(List<Mascaras> listMascaras, boolean soloAplicarReEstructuracion, byte[] propertiesMascara, String ubicacion, String uuidCxn, String uuidCxnMaestra);//JSA02//JSA03

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje getFilePropertiesMascara(String directorioReportesDelSistema, String uuidCxn);//JSA03
}
