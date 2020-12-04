/**
 * @author: Daniel
 * Fecha de Creación: --/--/--
 * Compañía: FineSoft
 * Descripción del programa: clase Bancos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:10/11/2011
 * Descripción:Se cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.CategoriasPuestos;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PercepcionesFijas;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface CategoriasPuestosDAOIF extends GenericDAO<CategoriasPuestos, Long> {

    public Mensaje agregar(CategoriasPuestos entity, String uuidCxn);

    public Mensaje actualizar(CategoriasPuestos entity, String uuidCxn);

    public Mensaje eliminar(CategoriasPuestos entity, String uuidCxn);

    public Mensaje getCategoriasPuestosAll(String uuidCxn);

    public Mensaje getCategoriasPuestosPorClave(String clave, String uuidCxn);

    Mensaje SaveCategoriaPuesto(List<PercepcionesFijas> agrega, Object[] eliminados, CategoriasPuestos entity, String uuidCxn);

    Mensaje DeleteCategoriaPuesto(CategoriasPuestos entity, String uuidCxn);

    Mensaje UpdateCategoriaPuesto(List<PercepcionesFijas> agrega, Object[] eliminados, CategoriasPuestos entity, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje saveDeleteCategoriasPuestos(List<CategoriasPuestos> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
