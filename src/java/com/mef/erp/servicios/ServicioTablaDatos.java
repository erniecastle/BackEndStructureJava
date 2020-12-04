/**
 * @author: Victor Fecha de Creacion: 06/06/2011 Compañia: Macropro Descripcion
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

import com.mef.erp.modelo.TablaDatosDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TablaBase;
import com.mef.erp.modelo.entidad.TablaDatos;
import com.mef.erp.modelo.entidad.TablaPersonalizada;
import java.util.Date;

/**
 *
 * // * @author yo
 */
public class ServicioTablaDatos implements ServicioTablaDatosIF {

    private TablaDatosDAO tablaDatosDAO;

    public Mensaje getTablaDatosAll(String uuidCxnMaestra) {
        return tablaDatosDAO.getTablaDatosAll(uuidCxnMaestra);
    }

    public Mensaje getListTablaDatosPorTablaBase(String clave, String uuidCxnMaestra) {
        return tablaDatosDAO.getListTablaDatosPorTablaBase(clave, uuidCxnMaestra);
    }

    public Mensaje agregar(TablaDatos entity, String uuidCxnMaestra) {
        return tablaDatosDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(TablaDatos entity, String uuidCxnMaestra) {
        return tablaDatosDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(TablaDatos tablaGenerada, String uuidCxnMaestra) {
        return tablaDatosDAO.eliminar(tablaGenerada, uuidCxnMaestra);
    }

    public Mensaje getTablaDatosPorTablas(TablaBase tablaBase, TablaPersonalizada tablaPersonalizada, String uuidCxnMaestra) {
        return tablaDatosDAO.getTablaDatosPorTablas(tablaBase, tablaPersonalizada, uuidCxnMaestra);
    }

    public Mensaje getTablaDatosPorTablasPorControlador(String controladores, TablaBase tablaBase, TablaPersonalizada tablaPersonalizada, String uuidCxnMaestra) {
        return tablaDatosDAO.getTablaDatosPorTablasPorControlador(controladores, tablaBase, tablaPersonalizada, uuidCxnMaestra);
    }

    public Mensaje consultaPorControladores(String controladores, String uuidCxnMaestra) {
        return tablaDatosDAO.consultaPorControladores(controladores, uuidCxnMaestra);
    }

    public Mensaje consultaPorListaControladores(String[] controladores, String uuidCxnMaestra) {
        return tablaDatosDAO.consultaPorListaControladores(controladores, uuidCxnMaestra);
    }

    public Mensaje getExisteDatosPorTablas(String tablaBaseClave, String tablaPersonalizadaClave, String uuidCxnMaestra) {
        return tablaDatosDAO.getExisteDatosPorTablas(tablaBaseClave, tablaPersonalizadaClave, uuidCxnMaestra);
    }

    public Mensaje getTablaDatosPorTablasPorControladorPorFiltrosEspeciales(Date controlFecha, Integer controlAnio, String controladores, String tablaBaseClave, String tablaPersonalizadaClave, String uuidCxnMaestra) {
        return tablaDatosDAO.getTablaDatosPorTablasPorControladorPorFiltrosEspeciales(controlFecha, controlAnio, controladores, tablaBaseClave, tablaPersonalizadaClave, uuidCxnMaestra);
    }

    public TablaDatosDAO getTablaDatosDAO() {
        return tablaDatosDAO;
    }

    public void setTablaDatosDAO(TablaDatosDAO tablaDatosDAO) {
        this.tablaDatosDAO = tablaDatosDAO;
    }
}
