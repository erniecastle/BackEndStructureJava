/**
 * @author: Ernesto Valenzuela Fecha de Creación: 13/04/2011 Compañía: Exito
 * Software. Descripción del programa: clase SERVICIO codigos postales, para
 * llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.RegistroPatronalDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Primas;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import java.util.List;

public class ServicioRegistroPatronal implements ServicioRegistroPatronalIF {

    private RegistroPatronalDAO registroPatronalDAO;

    public Mensaje agregar(RegistroPatronal entity, String uuidCxn) {//JSA01
        return registroPatronalDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(RegistroPatronal entity, String uuidCxn) {//JSA01
        return registroPatronalDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(RegistroPatronal entity, String uuidCxn) {//JSA01
        return registroPatronalDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getRegistroPatronalAll(String claveRazonesSocial, String uuidCxn) {//JSA01
        return registroPatronalDAO.getRegistroPatronalAll(claveRazonesSocial, uuidCxn);
    }

    public Mensaje getRegistroPatronalPorClave(String clave, String claveRazonesSocial, String uuidCxn) {//JSA01
        return registroPatronalDAO.getRegistroPatronalPorClave(clave, claveRazonesSocial, uuidCxn);
    }

    public Mensaje SaveRegistroPatronal(List<Primas> agrega, Object[] eliminados, RegistroPatronal entity, String uuidCxn) {//JSA01
        return registroPatronalDAO.SaveRegistroPatronal(agrega, eliminados, entity, uuidCxn);
    }

    public Mensaje UpdateRegistroPatronal(List<Primas> agrega, Object[] eliminados, RegistroPatronal entity, String uuidCxn) {//JSA01
        return registroPatronalDAO.UpdateRegistroPatronal(agrega, eliminados, entity, uuidCxn);
    }

    public Mensaje DeleteRegistroPatronal(RegistroPatronal entity, String uuidCxn) {//JSA01
        return registroPatronalDAO.DeleteRegistroPatronal(entity, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn) {//JSA01
        return registroPatronalDAO.consultaPorRangosPatronal(inicio, rango, camposWhere, camposWhereValores, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return registroPatronalDAO.consultaPorFiltrosPatronal(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje agregarListaRegistrosPatronales(List<RegistroPatronal> cambios, List<RegistroPatronal> temporales, List<Primas> cambioprima,
            Object[] clavesDelete, Object[] clavesPrimasDelete, int rango, String uuidCxn) {//JSA01
        return registroPatronalDAO.agregarListaRegistrosPatronales(cambios, temporales, cambioprima, clavesDelete, clavesPrimasDelete, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return registroPatronalDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje deleteListClavesRegistroPatronal(Object[] valores, String uuidCxn) {//JSA01
        return registroPatronalDAO.deleteListClavesRegistroPatronal(valores, uuidCxn);
    }

    public RegistroPatronalDAO getRegistroPatronalDAO() {
        return registroPatronalDAO;
    }

    public void setRegistroPatronalDAO(RegistroPatronalDAO registroPatronalDAO) {
        this.registroPatronalDAO = registroPatronalDAO;
    }
}
