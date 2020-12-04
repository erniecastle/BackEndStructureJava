/**
 * @author: Daniel Fecha de Creación: --/--/-- Compañía: FineSoft Descripción
 * del programa: clase Bancos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.CategoriasPuestos;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PercepcionesFijas;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioCategoriasPuestosIF {

    /*CategoriasPuestos*/
    public Mensaje agregar(CategoriasPuestos entity, String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(CategoriasPuestos entity, String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(CategoriasPuestos entity, String uuidCxn);

    /*List<CategoriasPuestos>*/
    public Mensaje getCategoriasPuestosAll(String uuidCxn);

    /*CategoriasPuestos*/
    public Mensaje getCategoriasPuestosPorClave(String clave, String uuidCxn);

    /*CategoriasPuestos*/
    Mensaje SaveCategoriaPuesto(List<PercepcionesFijas> agrega, Object[] eliminados, CategoriasPuestos entity, String uuidCxn);

    /*boolean*/
    Mensaje DeleteCategoriaPuesto(CategoriasPuestos entity, String uuidCxn);

    /*boolean*/
    Mensaje UpdateCategoriaPuesto(List<PercepcionesFijas> agrega, Object[] eliminados, CategoriasPuestos entity, String uuidCxn);

    /*List<CategoriasPuestos>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*List<CategoriasPuestos>*/
    Mensaje saveDeleteCategoriasPuestos(List<CategoriasPuestos> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

}
