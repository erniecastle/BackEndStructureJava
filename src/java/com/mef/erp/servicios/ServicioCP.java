/**
 * @author: Ernesto Castillo Fecha de Creación: 15/03/2011 Compañía: Exito
 * Software. Descripción del programa: clase SERVICIO codigos postales, para
 * llamados a metodos de objeto DAO
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

import com.mef.erp.modelo.CpDAO;
import com.mef.erp.modelo.entidad.Cp;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioCP implements ServicioCPIF {

    private CpDAO cpDAO;


    public Mensaje getCpAll(String uuidCxn) {
        return cpDAO.getCpAll(uuidCxn);
    }

    public Mensaje getCpPorClave(String clave ,String uuidCxn) {//AAP01
        return cpDAO.getCpPorClave(clave,uuidCxn);
    }

    public Mensaje agregar(Cp entity,String uuidCxn) {
        return cpDAO.agregar(entity,uuidCxn);
    }

    public Mensaje actualizar(Cp entity,String uuidCxn) {
        return cpDAO.actualizar(entity,uuidCxn);
    }

    public Mensaje eliminar(Cp entity,String uuidCxn) {
        return cpDAO.eliminar(entity ,uuidCxn );
    }

    public Mensaje getCpPorCiudades(String claveCiudad,String uuidCxn) {
        return cpDAO.getCpPorCiudades(claveCiudad,uuidCxn);
    }

    public Mensaje getCpPorMunicipio(String claveMunicipio,String uuidCxn) {
        return cpDAO.getCpPorMunicipio(claveMunicipio,uuidCxn);
    }

    public Mensaje getCpPorEstado(String claveEstado,String uuidCxn) {
        return cpDAO.getCpPorEstado(claveEstado,uuidCxn);
    }

    public Mensaje getCpPorPais(String clavePais,String uuidCxn) {
        return cpDAO.getCpPorPais(clavePais,uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango,String uuidCxn) {
        return cpDAO.consultaPorFiltrosCP(query, campos, valores, inicio, rango,uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango,String uuidCxn) {
        return cpDAO.consultaPorRangos(inicio, rango,uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor,String uuidCxn) {
        return cpDAO.existeDato(campo, valor,uuidCxn);
    }

    public Mensaje saveDeleteCp(List<Cp> entitysCambios, Object[] clavesDelete, int rango,String uuidCxn) {
        return cpDAO.saveDeleteCp(entitysCambios, clavesDelete, rango,uuidCxn);
    }

    public CpDAO getCpDAO() {
        return cpDAO;
    }

    public void setCpDAO(CpDAO cpDAO) {
        this.cpDAO = cpDAO;
    }

   
}
