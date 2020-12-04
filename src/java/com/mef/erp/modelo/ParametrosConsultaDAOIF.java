/**
 * @author: Victor Lopez Compañía: Macropro. Fecha de Creación: 28/09/2012
 * Descripción del programa: interface ParametrosConsultaDAOIF para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ParametrosConsulta;

public interface ParametrosConsultaDAOIF {

    Mensaje agregar(ParametrosConsulta entity, Contenedor contenedorGrupoMenu, String uuidCxn);

    Mensaje actualizar(ParametrosConsulta entity, Contenedor contenedorGrupoMenu, String uuidCxn);

    Mensaje eliminar(ParametrosConsulta entity, String uuidCxn);

    Mensaje getParametrosConsultaAll(String uuidCxn);

    public Mensaje getParametrosConsultaAllEspecifico(String uuidCxn);

    public Mensaje getParametrosConsultaPorID(Long idParametrosConsulta, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorFiltrosParametros(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje PorGrupoMenuFuenteDatos(String fuenteDatos, Integer idContenedor, String uuidCxn);

    public Mensaje eliminarEspecifico(Long idReporte, String uuidCxn);
}
