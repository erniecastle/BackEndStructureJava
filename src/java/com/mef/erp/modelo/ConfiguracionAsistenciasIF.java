/**
 * @author: Ernesto Castillo Fecha de Creación: 01/12/2011 Compañía: Exito
 * Software. Descripción del programa: inteface de clase de configuracion
 * asistencias para llamados a metodos de HIBERNATE
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
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.ConfiguracionAsistencias;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ConfiguracionAsistenciasIF extends GenericDAO<ConfiguracionAsistencias, Long> {

    Mensaje agregar(ConfiguracionAsistencias entity, String uuidCxn);

    Mensaje getExcepcionesPorConfiguracionAsistencias(String clave, String uuidCxn);

    Mensaje getConfiguracionAsistenciasAll(String claveRazonesSocial, String uuidCxn);

    public Mensaje PorGrupoMenu(String idContenedor, String claveRazonSocial, String uuidCxn);//JSA01

    Mensaje saveDeleteConfiguracionAsistencias(List<ConfiguracionAsistencias> AgreModif, List<ConfiguracionAsistencias> Ordenados, List<ConfiguracionAsistencias> eliminados, String uuidCxn);

    Mensaje buscaPorIdYRazonSocial(Long id, String claveRazonSocial, String uuidCxn);
    
    Mensaje buscaConfiguracionAsistenciasSistema(Long id, String uuidCxn);
    
    Mensaje getAllConfiguracionAsistenciasSistema(String uuidCxn);
}
