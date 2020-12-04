/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ModuloDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Modulo;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public class ServicioModulo implements ServicioModuloIF {

    private ModuloDAO moduloDAO;

    public Mensaje agregar(Modulo entity, String uuidCxn) {

        return moduloDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Modulo entity, String uuidCxn) {

        return moduloDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Modulo entity, String uuidCxn) {

        return moduloDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getModuloAll(String uuidCxn) {

        return moduloDAO.getModuloAll(uuidCxn);
    }

    public Mensaje getModuloPorNombre(String nombreModulo, String uuidCxn) {

        return moduloDAO.getModuloPorNombre(nombreModulo, uuidCxn);
    }

    public Mensaje getModuloPorSistemas(String id, String uuidCxn) {

        return moduloDAO.getModuloPorSistemas(id, uuidCxn);
    }

    public Mensaje getModuloPorClave(String clave, String uuidCxn) {

        return moduloDAO.getModuloPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {

        return moduloDAO.consultaPorFiltrosModulo(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {

        return moduloDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {

        return moduloDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteModulo(List<Modulo> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {

        return moduloDAO.saveDeleteModulo(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public ModuloDAO getModuloDAO() {

        return moduloDAO;
    }

    public void setModuloDAO(ModuloDAO moduloDAO) {

        this.moduloDAO = moduloDAO;
    }
}
