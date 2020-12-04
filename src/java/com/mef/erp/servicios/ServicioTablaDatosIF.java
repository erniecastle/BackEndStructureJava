/**
 * @author: Victor Fecha de Creaci�n: 06/06/2011 Compañia: Macropro Descripcion
 * del programa: clase TablaPersonalizada para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 08-07-2011
 * Descripcion: Se aplicaron PDM.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripcion:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:09/05/2013 Descripcion:se
 * agrego el metodo saveDeleteTablaDatos para cuando se elimina una tablaDatos
 * este surja afecto en TablaBase primero antes de eliminarse.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TablaBase;
import com.mef.erp.modelo.entidad.TablaDatos;
import com.mef.erp.modelo.entidad.TablaPersonalizada;
import java.util.Date;

/**
 *
 * @author victor
 */
public interface ServicioTablaDatosIF {

    /*List<TablaDatos>*/
    public Mensaje getTablaDatosAll(String uuidCxnMaestra);

    /*List<TablaDatos>*/
    public Mensaje getListTablaDatosPorTablaBase(String clave, String uuidCxnMaestra);

    /*TablaDatos*/
    public Mensaje agregar(TablaDatos entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje actualizar(TablaDatos entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje eliminar(TablaDatos entity, String uuidCxnMaestra);//JEVC05

    /*List<TablaDatos>*/
    public Mensaje getTablaDatosPorTablas(TablaBase tablaBase, TablaPersonalizada tablaPersonalizada, String uuidCxnMaestra);

    /*TablaDatos*/
    Mensaje getTablaDatosPorTablasPorControlador(String controladores, TablaBase tablaBase, TablaPersonalizada tablaPersonalizada, String uuidCxnMaestra);

    /*List<TablaDatos>*/
    Mensaje consultaPorControladores(String controladores, String uuidCxnMaestra);

    /*List<TablaDatos>*/
    Mensaje consultaPorListaControladores(String[] controladores, String uuidCxnMaestra);

    /*boolean*/
    Mensaje getExisteDatosPorTablas(String tablaBaseClave, String tablaPersonalizadaClave, String uuidCxnMaestra);

    /*TablaDatos*/
    Mensaje getTablaDatosPorTablasPorControladorPorFiltrosEspeciales(Date controlFecha, Integer controlAnio, String controladores, String tablaBaseClave, String tablaPersonalizadaClave, String uuidCxnMaestra);
}
