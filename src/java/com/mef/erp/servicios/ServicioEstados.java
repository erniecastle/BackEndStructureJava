/**
 * @author: Ernesto Valenzuela Fecha de Creación: 15/03/2011 Compañía: Exito
 * Software. Descripción del programa: clase SERVICIO estados, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.EstadosDAO;
import com.mef.erp.modelo.entidad.Estados;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioEstados implements ServicioEstadosIF {

    private EstadosDAO estadosDAO;
   

    public Mensaje agregar(Estados entity, String uuidCxn) {
        return estadosDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Estados entity, String uuidCxn) {
        return estadosDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Estados entity, String uuidCxn) {
        return estadosDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getEstadosAll(String uuidCxn) {
        return estadosDAO.getEstadosAll(uuidCxn);
    }

    public Mensaje getEstadosPorClave(String clave, String uuidCxn) {//AAP01
        return estadosDAO.getEstadosPorClave(clave, uuidCxn);
    }

    public Mensaje getEstadosPorPais(String clavePais, String uuidCxn) {
        return estadosDAO.getEstadosPorPais(clavePais, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {
        return estadosDAO.consultaPorFiltrosEstado(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return estadosDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return estadosDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteEstados(List<Estados> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return estadosDAO.saveDeleteEstados(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public EstadosDAO getEstadosDAO() {
        return estadosDAO;
    }

    public void setEstadosDAO(EstadosDAO estadosDAO) {
        this.estadosDAO = estadosDAO;
    }

   

}
