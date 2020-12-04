/**
 * @author: Victor Lopez Fecha de Creación: 28/09/2012 Compañía: MacroPro.
 * Descripción del programa: interface servicio ParametrosConsulta
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ParametrosConsulta;

public interface ServicioParametrosConsultaIF {

    /*ParametrosConsulta*/
    Mensaje agregar(ParametrosConsulta entity, Contenedor contenedorGrupoMenu, String uuidCxn);

    /*ParametrosConsulta*/
    Mensaje actualizar(ParametrosConsulta entity, Contenedor contenedorGrupoMenu, String uuidCxn);

    /*boolean*/
    Mensaje eliminar(ParametrosConsulta entity, String uuidCxn);

    /*List<ParametrosConsulta> */
    Mensaje getParametrosConsultaAll(String uuidCxn);

    /*List<ParametrosConsulta> */
    Mensaje getParametrosConsultaAllEspecifico(String uuidCxn);

    /*ParametrosConsulta*/
    public Mensaje getParametrosConsultaPorID(Long idParametrosConsulta, String uuidCxn);

    /*List<ParametrosConsulta> */
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<ParametrosConsulta> */
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<ParametrosConsulta> */
    Mensaje PorGrupoMenuFuenteDatos(String fuenteDatos, Integer idContenedor, String uuidCxn);

    /*ParametrosConsulta*/
    public Mensaje eliminarEspecifico(Long idReporte, String uuidCxn);

}
