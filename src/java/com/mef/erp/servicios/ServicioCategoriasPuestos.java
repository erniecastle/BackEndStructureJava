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

import com.mef.erp.modelo.CategoriasPuestosDAO;
import com.mef.erp.modelo.entidad.CategoriasPuestos;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PercepcionesFijas;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioCategoriasPuestos implements ServicioCategoriasPuestosIF {

    private CategoriasPuestosDAO categoriasPuestosDAO;

    public Mensaje agregar(CategoriasPuestos entity, String uuidCxn) {
        return getCategoriasPuestosDAO().agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(CategoriasPuestos entity, String uuidCxn) {
        return getCategoriasPuestosDAO().actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(CategoriasPuestos entity, String uuidCxn) {
        return getCategoriasPuestosDAO().eliminar(entity, uuidCxn);
    }

    public Mensaje getCategoriasPuestosAll(String uuidCxn) {
        return getCategoriasPuestosDAO().getCategoriasPuestosAll(uuidCxn);
    }

    public Mensaje getCategoriasPuestosPorClave(String clave, String uuidCxn) {
        return getCategoriasPuestosDAO().getCategoriasPuestosPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return getCategoriasPuestosDAO().consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return getCategoriasPuestosDAO().existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteCategoriasPuestos(List<CategoriasPuestos> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return getCategoriasPuestosDAO().saveDeleteCategoriasPuestos(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public Mensaje SaveCategoriaPuesto(List<PercepcionesFijas> agrega, Object[] eliminados, CategoriasPuestos entity, String uuidCxn) {
        return getCategoriasPuestosDAO().SaveCategoriaPuesto(agrega, eliminados, entity, uuidCxn);
    }

    public Mensaje DeleteCategoriaPuesto(CategoriasPuestos entity, String uuidCxn) {
        return getCategoriasPuestosDAO().DeleteCategoriaPuesto(entity, uuidCxn);
    }

    public Mensaje UpdateCategoriaPuesto(List<PercepcionesFijas> agrega, Object[] eliminados, CategoriasPuestos entity, String uuidCxn) {
        return getCategoriasPuestosDAO().UpdateCategoriaPuesto(agrega, eliminados, entity, uuidCxn);
    }

    public CategoriasPuestosDAO getCategoriasPuestosDAO() {
        return categoriasPuestosDAO;
    }

    public void setCategoriasPuestosDAO(CategoriasPuestosDAO categoriasPuestosDAO) {
        this.categoriasPuestosDAO = categoriasPuestosDAO;
    }
}
