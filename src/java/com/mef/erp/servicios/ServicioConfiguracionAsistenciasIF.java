/**
 * @author: Ernesto Castillo Fecha de Creación: 7/12/2011 Compañía: Exito
 * Software. Descripción del programa: Interface para servicio Configuracion de
 * Asistencias
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:10/NOV/2012 Descripción:Se agrego el
 * metodo PorGrupoMenu para regresar las configuraciones con ese tipo de
 * contenedor.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ConfiguracionAsistencias;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioConfiguracionAsistenciasIF {

    /*ConfiguracionAsistencias*/
    Mensaje agregar(ConfiguracionAsistencias entity, String uuidCxn);

    /*ConfiguracionAsistencias*/
    Mensaje getExcepcionesPorConfiguracionAsistencias(String clave, String uuidCxn);

    /* List<ConfiguracionAsistencias> */
    Mensaje getConfiguracionAsistenciasAll(String claveRazonesSocial, String uuidCxn);

    /* List<ConfiguracionAsistencias> */
    Mensaje PorGrupoMenu(String idContenedor, String claveRazonSocial, String uuidCxn);//JSA01

    /* List<ConfiguracionAsistencias> */
    Mensaje saveDeleteConfiguracionAsistencias(List<ConfiguracionAsistencias> AgreModif, List<ConfiguracionAsistencias> Ordenados, List<ConfiguracionAsistencias> eliminados, String uuidCxn);

    /*ConfiguracionAsistencias*/
    Mensaje buscaPorIdYRazonSocial(Long id, String claveRazonSocial, String uuidCxn);

    /*ConfiguracionAsistencias*/
    Mensaje buscaConfiguracionAsistenciasSistema(Long id, String uuidCxn);

    /* List<ConfiguracionAsistencias> */
    Mensaje getAllConfiguracionAsistenciasSistema(String uuidCxn);

}
